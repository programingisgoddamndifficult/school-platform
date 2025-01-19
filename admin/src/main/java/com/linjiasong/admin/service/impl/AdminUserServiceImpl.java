package com.linjiasong.admin.service.impl;

import com.linjiasong.admin.excepiton.AdminBaseResponse;
import com.linjiasong.admin.feign.UserServiceClient;
import com.linjiasong.admin.service.AdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author linjiasong
 * @date 2025/01/19 15:23
 */
@Service
public class AdminUserServiceImpl implements AdminUserService {

    @Autowired
    UserServiceClient userServiceClient;

    @Override
    public AdminBaseResponse banUser(Long userId) {
        return userServiceClient.banUser(userId);
    }
}
