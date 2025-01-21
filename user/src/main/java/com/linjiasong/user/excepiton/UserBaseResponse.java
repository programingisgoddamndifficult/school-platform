package com.linjiasong.user.excepiton;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author linjiasong
 * @date 2025/1/13 下午5:18
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserBaseResponse<T> implements Serializable {
    /**
     * 响应状态码
     */
    private String code;

    /**
     * 响应描述
     */
    private String msg;

    /**
     * 响应业务数据
     */
    private T data;

    public static <T> UserBaseResponse<T> success(){
        return UserBaseResponse.<T>builder().msg("success").code("200").build();
    }

    public static <T> UserBaseResponse<T> success(T data){
        return UserBaseResponse.<T>builder().msg("success").code("200").data(data).build();
    }
}
