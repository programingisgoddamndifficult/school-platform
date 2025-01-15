package com.linjiasong.user.gateway.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.linjiasong.user.entity.UserInfo;
import com.linjiasong.user.gateway.UserGateway;
import com.linjiasong.user.mapper.UserInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author linjiasong
 * @date 2025/1/13 下午5:37
 */
@Service
public class UserGatewayImpl implements UserGateway {

    @Autowired
    UserInfoMapper userInfoMapper;

    @Override
    public UserInfo selectByUsername(String username) {
        return userInfoMapper.selectOne(new QueryWrapper<UserInfo>().eq("username", username));
    }

    @Override
    public UserInfo selectByPhone(String phone) {
        return userInfoMapper.selectOne(new QueryWrapper<UserInfo>().eq("phone", phone));
    }

    @Override
    public UserInfo selectByEmail(String email) {
        return userInfoMapper.selectOne(new QueryWrapper<UserInfo>().eq("email", email));
    }

    @Override
    public boolean insert(UserInfo userInfo) {
        return userInfoMapper.insert(userInfo) >= 1;
    }

    @Override
    public UserInfo selectById(Long id) {
        return userInfoMapper.selectById(id);
    }
}
