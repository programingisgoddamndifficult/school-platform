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
public class UserBaseResponse implements Serializable {
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
}
