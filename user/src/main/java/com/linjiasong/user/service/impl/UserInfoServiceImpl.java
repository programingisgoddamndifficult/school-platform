package com.linjiasong.user.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.linjiasong.user.constant.UserInfoContext;
import com.linjiasong.user.entity.UserInfo;
import com.linjiasong.user.entity.dto.UserInfoDTO;
import com.linjiasong.user.entity.dto.UserLoginDTO;
import com.linjiasong.user.entity.vo.UserInfoVo;
import com.linjiasong.user.excepiton.BizException;
import com.linjiasong.user.excepiton.UserBaseResponse;
import com.linjiasong.user.gateway.UserGateWay;
import com.linjiasong.user.gateway.UserLikeGateWay;
import com.linjiasong.user.mapper.UserInfoMapper;
import com.linjiasong.user.service.UserInfoService;
import com.linjiasong.user.utils.DESUtil;
import com.linjiasong.user.utils.MD5Util;
import com.linjiasong.user.utils.TokenUtil;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
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
    private UserGateWay userGateWay;

    @Autowired
    private UserLikeGateWay userLikeGateWay;

    @Override
    public UserBaseResponse signUp(UserInfoDTO userInfo) {
        checkSignUpInfo(userInfo);

        if (!userGateWay.insert(userInfoDtoToUserInfo(userInfo))) {
            throw new BizException("注册失败,服务异常");
        }

        return UserBaseResponse.builder().code("200").msg("success").build();
    }

    @Override
    public UserBaseResponse login(UserLoginDTO userLoginDTO, HttpServletResponse response) {
        checkUserLoginParam(userLoginDTO);

        userLoginDTO.setPassword(MD5Util.md5Digest(userLoginDTO.getPassword()));

        UserInfo userInfoByUserName = userGateWay.selectByUsername(userLoginDTO.getUsername());
        if (userInfoByUserName == null) {
            throw new BizException("登陆用户名有误或不存在");
        }

        if (!userInfoByUserName.getPassword().equals(userLoginDTO.getPassword())) {
            throw new BizException("密码不正确");
        }

        response.setHeader("Authorization", "Bearer " + TokenUtil.generateToken(JSON.toJSONString(userInfoByUserName)));
        return UserBaseResponse.builder().code("200").msg("success").build();
    }

    @Override
    public UserBaseResponse getUserInfo() {
        UserInfo userInfo = UserInfoContext.get();
        return UserBaseResponse.builder().code("200").msg("success").data(UserInfoVo.build(userInfo,userLikeGateWay.getLikeNums(userInfo.getId()))).build();
    }

    private void checkUserLoginParam(UserLoginDTO userLoginDTO) {
        if (userLoginDTO == null || userLoginDTO.getLoginType() == null) {
            throw new BizException("入参异常");
        }

        String loginType = userLoginDTO.getLoginType();

        switch (loginType) {
            case "username":
                checkUsernameLoginTypeParam(userLoginDTO);
                break;
            case "phone":
                throw new BizException("手机号的登陆方式暂未开通");
            default:
                throw new BizException("登陆方式有误");
        }
    }

    private void checkUsernameLoginTypeParam(UserLoginDTO userLoginDTO) {
        if (userLoginDTO.getUsername() == null) {
            throw new BizException("登陆用户名为空");
        }

        if (userLoginDTO.getPassword() == null) {
            throw new BizException("登陆密码为空");
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

        if(userInfo.getPhone().length() != 11){
            throw new BizException("手机号码格式不正确");
        }

        userInfo.setPassword(MD5Util.md5Digest(userInfo.getPassword()));
        userInfo.setPhone(DESUtil.encrypt(userInfo.getPhone()));
        if (userInfo.getEmail() != null) {
            userInfo.setEmail(DESUtil.encrypt(userInfo.getEmail()));
        }

        UserInfo userInfoByUsername = userGateWay.selectByUsername(userInfo.getUsername());
        if (userInfoByUsername != null) {
            throw new BizException("注册失败，用户名重复");
        }

        UserInfo userInfoByPhone = userGateWay.selectByPhone(userInfo.getPhone());
        if (userInfoByPhone != null) {
            throw new BizException("注册失败，电话号码重复");
        }

        UserInfo userInfoByEmail = userGateWay.selectByEmail(userInfo.getEmail());
        if (userInfoByEmail != null) {
            throw new BizException("注册失败，邮箱号码重复");
        }
    }

    private UserInfo userInfoDtoToUserInfo(UserInfoDTO userInfoDTO) {
        return UserInfo.builder()
                .username(userInfoDTO.getUsername())
                .password(userInfoDTO.getPassword())
                .phone(userInfoDTO.getPhone())
                .email(userInfoDTO.getEmail())
                .build();
    }

}
