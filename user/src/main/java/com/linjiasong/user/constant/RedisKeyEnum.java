package com.linjiasong.user.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.concurrent.TimeUnit;

/**
 * @author linjiasong
 * @date 2025/1/20 下午3:45
 */
@AllArgsConstructor
@Getter
public enum RedisKeyEnum {

    USER_BAN("user:ban:%s", "封禁用户id", 0L, TimeUnit.MINUTES),
    ;

    /**
     * redis key
     */
    private String key;
    /**
     * 描述
     */
    private String desc;
    /**
     * 过期时间
     */
    private Long expiryTime;
    /**
     * 时间单位
     */
    private TimeUnit timeUnit;

}
