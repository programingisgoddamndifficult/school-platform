package com.linjiasong.user.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author linjiasong
 * @date 2025/1/13 下午5:28
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoDTO {
    private String username;

    private String password;

    private String phone;

    private String email;
}
