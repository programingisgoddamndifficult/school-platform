package com.linjiasong.admin.service;

import com.linjiasong.admin.excepiton.AdminBaseResponse;

/**
 * @author linjiasong
 * @date 2025/01/19 15:23
 */
public interface AdminUserService {

    /**
     * 封禁用户
     * @param userId userId
     * @return AdminBaseResponse
     */
    AdminBaseResponse banUser(Long userId);

}
