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

    USER_LOGIN("user:login:%s", "用户登陆标识", 7L, TimeUnit.DAYS),

    POINT_ARTICLE("user:point_article:%s", "用户点击文章", 7L, TimeUnit.DAYS),
    POINT_ARTICLE_SCORED("user:point_article_scored", "用户点击文章排序", 7L, TimeUnit.DAYS),
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
