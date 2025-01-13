package com.linjiasong.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.linjiasong.user.entity.UserInfo;
import com.linjiasong.user.entity.dto.UserInfoDTO;
import com.linjiasong.user.gateway.UserGateWay;
import com.linjiasong.user.mapper.UserInfoMapper;
import com.linjiasong.user.service.UserInfoService;
import excepiton.BizException;
import excepiton.UserBaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author linjiasong
 * @date 2025/1/13 下午4:34
 */

@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {

    @Autowired
    private UserGateWay userGateWay;

    @Override
    public UserBaseResponse signUp(UserInfoDTO userInfo) {
        checkSignUpInfo(userInfo);

        if (!userGateWay.insert(UserInfoDtoToUserInfo(userInfo))) {
            throw new BizException("注册失败,服务异常");
        }

        return UserBaseResponse.builder().code("200").msg("success").build();
    }

    private void checkSignUpInfo(UserInfoDTO userInfo) {
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

    private UserInfo UserInfoDtoToUserInfo(UserInfoDTO userInfoDTO) {
        return UserInfo.builder()
                .username(userInfoDTO.getUsername())
                .password(userInfoDTO.getPassword())
                .phone(userInfoDTO.getPhone())
                .email(userInfoDTO.getEmail())
                .build();
    }
}
