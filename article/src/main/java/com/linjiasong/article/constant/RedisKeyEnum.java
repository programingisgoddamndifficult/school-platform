package com.linjiasong.article.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.concurrent.TimeUnit;

/**
 * @author linjiasong
 * @date 2025/1/22 下午3:04
 */
@AllArgsConstructor
@Getter
public enum RedisKeyEnum {

    POINT_ARTICLE("user:point_article:%s", "用户点击文章", 7L, TimeUnit.DAYS),
    POINT_ARTICLE_SCORED("user:point_article_scored", "用户点击文章排序", 7L, TimeUnit.DAYS);

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
