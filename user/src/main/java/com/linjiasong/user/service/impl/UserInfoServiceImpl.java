package com.linjiasong.user.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.linjiasong.user.constant.RedisKeyEnum;
import com.linjiasong.user.constant.UserInfoContext;
import com.linjiasong.user.entity.UserInfo;
import com.linjiasong.user.entity.dto.UserInfoDTO;
import com.linjiasong.user.entity.dto.UserLoginDTO;
import com.linjiasong.user.entity.vo.UserInfoVo;
import com.linjiasong.user.excepiton.BizException;
import com.linjiasong.user.excepiton.UserBaseResponse;
import com.linjiasong.user.gateway.UserGateway;
import com.linjiasong.user.gateway.UserLikeGateway;
import com.linjiasong.user.mapper.UserInfoMapper;
import com.linjiasong.user.mq.RabbitMQProducer;
import com.linjiasong.user.mq.config.RabbitMQTopicConfig;
import com.linjiasong.user.mq.dto.MqBaseExchangeDTO;
import com.linjiasong.user.mq.dto.UserDeleteDTO;
import com.linjiasong.user.mq.enums.MqExchangeTypeEnum;
import com.linjiasong.user.service.UserInfoService;
import com.linjiasong.user.utils.DESUtil;
import com.linjiasong.user.utils.MD5Util;
import com.linjiasong.user.utils.TokenUtil;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author linjiasong
 * @date 2025/1/13 下午4:34
 */

@Service
@Slf4j
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {

    @Autowired
    private UserGateway userGateway;

    @Autowired
    private UserLikeGateway userLikeGateway;

    @Autowired
    private RedissonClient redissonClient;

    @Autowired
    private RabbitMQProducer rabbitMQProducer;

    @Override
    public UserBaseResponse<?> signUp(UserInfoDTO userInfo) {
        checkSignUpInfo(userInfo);

        if (!userGateway.insert(UserInfo.userInfoDtoToUserInfo(userInfo))) {
            throw new BizException("注册失败,服务异常");
        }

        return UserBaseResponse.builder().code("200").msg("success").build();
    }

    @Override
    public UserBaseResponse<?> login(UserLoginDTO userLoginDTO, HttpServletResponse response) {
        checkUserLoginParam(userLoginDTO);

        userLoginDTO.setPassword(MD5Util.md5Digest(userLoginDTO.getPassword()));

        UserInfo userInfoByUserName = userGateway.selectByUsername(userLoginDTO.getUsername());
        if (userInfoByUserName == null) {
            throw new BizException("登陆用户名有误或不存在");
        }

        //封禁用户禁止登陆
        if (userInfoByUserName.getIsBan() == 1) {
            throw new BizException("您的账号存在异常，暂时不允许使用");
        }

        if (!userInfoByUserName.getPassword().equals(userLoginDTO.getPassword())) {
            throw new BizException("密码不正确");
        }

        String token = "Bearer " + TokenUtil.generateToken(JSON.toJSONString(userInfoByUserName));

        RBucket<String> bucket = redissonClient.getBucket(String.format(RedisKeyEnum.USER_LOGIN.getKey(), userInfoByUserName.getId()));
        if (bucket.isExists()) {
            bucket.delete();
        }

        bucket.set(token);
        response.setHeader("Authorization", token);
        return UserBaseResponse.builder().code("200").msg("success").build();
    }

    @Override
    public UserBaseResponse<?> loginOut() {
        RBucket<String> bucket = redissonClient.getBucket(String.format(RedisKeyEnum.USER_LOGIN.getKey(), UserInfoContext.get().getId()));
        bucket.delete();
        return UserBaseResponse.builder().code("200").msg("success").build();
    }

    @Override
    public UserBaseResponse<?> getUserInfo() {
        UserInfo userInfo = UserInfoContext.get();
        return UserBaseResponse.builder().code("200").msg("success").data(UserInfoVo.build(userInfo, userLikeGateway.getLikeNums(userInfo.getId()))).build();
    }

    @Override
    public UserBaseResponse<?> banUser(Long userId) {
        if (!userGateway.banUser(userId)) {
            throw new BizException("服务异常");
        }

        RBucket<Long> bucket = redissonClient.getBucket(String.format(RedisKeyEnum.USER_BAN.getKey(), userId));
        if (bucket.isExists()) {
            bucket.delete();
        } else {
            bucket.set(userId);
        }

        return UserBaseResponse.builder().code("200").msg("success").build();
    }

    @Override
    public UserBaseResponse<?> userDelete() {
        if (!userGateway.userDelete()) {
            throw new BizException("服务异常");
        }

        rabbitMQProducer.sendMessage(RabbitMQTopicConfig.TOPIC_EXCHANGE_USER_ARTICLE, RabbitMQTopicConfig.TOPIC_USER_ARTICLE,
                MqBaseExchangeDTO.build(MqExchangeTypeEnum.USER_DELETE, UserDeleteDTO.build(UserInfoContext.get().getId())));

        redissonClient.getBucket(String.format(RedisKeyEnum.USER_LOGIN.getKey(), UserInfoContext.get().getId())).delete();

        return UserBaseResponse.success();
    }

    private void checkUserLoginParam(UserLoginDTO userLoginDTO) {
        if (userLoginDTO == null || userLoginDTO.getLoginType() == null) {
            throw new BizException("入参异常");
        }

        String loginType = userLoginDTO.getLoginType();

        switch (loginType) {
            case "username":
                userLoginDTO.checkUsernameLoginTypeParam();
                break;
            case "phone":
                throw new BizException("手机号的登陆方式暂未开通");
            default:
                throw new BizException("登陆方式有误");
        }
    }

    private void checkSignUpInfo(UserInfoDTO userInfo) {
        if (userInfo == null) {
            throw new BizException("注册失败,请传递参数");
        }

        if (userInfo.getUsername() == null) {
            throw new BizException("注册失败,请填写用户名");
        }

        if (userInfo.getPassword() == null) {
            throw new BizException("注册失败,请填写密码");
        }

        if (userInfo.getPhone() == null) {
            throw new BizException("注册失败,请填写手机号");
        }

        if (userInfo.getPhone().length() != 11) {
            throw new BizException("手机号码格式不正确");
        }

        userInfo.setPassword(MD5Util.md5Digest(userInfo.getPassword()));
        userInfo.setPhone(DESUtil.encrypt(userInfo.getPhone()));
        if (userInfo.getEmail() != null) {
            userInfo.setEmail(DESUtil.encrypt(userInfo.getEmail()));
        }

        UserInfo userInfoByUsername = userGateway.selectByUsername(userInfo.getUsername());
        if (userInfoByUsername != null) {
            throw new BizException("注册失败，用户名重复");
        }

        UserInfo userInfoByPhone = userGateway.selectByPhone(userInfo.getPhone());
        if (userInfoByPhone != null) {
            throw new BizException("注册失败，电话号码重复");
        }

        UserInfo userInfoByEmail = userGateway.selectByEmail(userInfo.getEmail());
        if (userInfoByEmail != null) {
            throw new BizException("注册失败，邮箱号码重复");
        }
    }
}
