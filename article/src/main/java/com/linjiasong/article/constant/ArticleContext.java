package com.linjiasong.article.constant;

import com.linjiasong.article.entity.UserInfo;

/**
 * @author linjiasong
 * @date 2025/1/15 下午4:55
 */
public class ArticleContext {
    public static ThreadLocal<UserInfo> THREAD_LOCAL = new ThreadLocal<>();

    public static void set(UserInfo userInfo) {
        THREAD_LOCAL.set(userInfo);
    }

    public static UserInfo get() {
        return THREAD_LOCAL.get();
    }

    public static void remove(){
        THREAD_LOCAL.remove();
    }

}
