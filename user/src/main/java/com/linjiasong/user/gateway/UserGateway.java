package com.linjiasong.user.gateway;

import com.linjiasong.user.entity.UserInfo;

/**
 * @author linjiasong
 * @date 2025/1/13 下午5:35
 */
public interface UserGateway {

    UserInfo selectByUsername(String username);

    UserInfo selectByPhone(String phone);

    UserInfo selectByEmail(String email);

    boolean insert(UserInfo userInfo);

    UserInfo selectById(Long id);
}
