package com.linjiasong.admin.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.concurrent.TimeUnit;

/**
 * @author linjiasong
 * @date 2025/1/23 下午5:35
 */
@AllArgsConstructor
@Getter
public enum RedisKeyEnum {

    ARTICLE_CHECK_LIST("article:check_list", "管理员check文章list", 14L, TimeUnit.DAYS),
    ARTICLE_DO_CHECK("article:do_check:%s", "被check的文章id", 7L, TimeUnit.DAYS),
    ADMIN_LOGIN("admin:login:%s", "管理员登陆标识", 7L, TimeUnit.DAYS),
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
