package com.linjiasong.user.entity.dto;

import com.linjiasong.user.excepiton.BizException;
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

    public void checkUsernameLoginTypeParam() {
        if (this.getUsername() == null) {
            throw new BizException("登陆用户名为空");
        }

        if (this.getPassword() == null) {
            throw new BizException("登陆密码为空");
        }
    }
}
