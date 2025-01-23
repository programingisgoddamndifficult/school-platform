package com.linjiasong.admin.excepiton;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author linjiasong
 * @date 2025/1/17 下午2:28
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdminBaseResponse implements Serializable {
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
    private Object data;

    public static AdminBaseResponse success() {
        return AdminBaseResponse.builder().code("200").msg("success").build();
    }

    public static AdminBaseResponse success(Object obj) {
        return AdminBaseResponse.builder().code("200").data(obj).msg("success").build();
    }
}
