package com.linjiasong.user.entity.dto;

import lombok.Data;

/**
 * @author linjiasong
 * @date 2025/1/14 下午4:38
 */
@Data
public class UserLoginDTO {
    private String username;

    private String password;

    private String phone;

    /**
     * 登陆方式
     */
    private String loginType;
}
