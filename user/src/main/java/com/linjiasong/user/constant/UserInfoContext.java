package com.linjiasong.user.constant;

import com.linjiasong.user.entity.UserInfo;

/**
 * @author linjiasong
 * @date 2025/01/14 22:01
 */

public class UserInfoContext {

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
