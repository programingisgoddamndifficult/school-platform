package com.linjiasong.admin.entity.dto;

import lombok.Data;

/**
 * @author linjiasong
 * @date 2025/1/17 下午2:52
 */
@Data
public class AdminDTO {
    private String username;

    private String password;

    public boolean checkParam(){
        if(username.length() > 20){
            return false;
        }

        if(password.length() > 36){
            return false;
        }

        return true;
    }
}
