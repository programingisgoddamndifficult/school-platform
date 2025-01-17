package com.linjiasong.admin.constant;

import com.linjiasong.admin.entity.AdminInfo;

/**
 * @author linjiasong
 * @date 2025/1/17 下午2:23
 */
public class AdminInfoContext {
    public static ThreadLocal<AdminInfo> THREAD_LOCAL = new ThreadLocal<>();

    public static void set(AdminInfo adminInfo) {
        THREAD_LOCAL.set(adminInfo);
    }

    public static AdminInfo get() {
        return THREAD_LOCAL.get();
    }

    public static void remove() {
        THREAD_LOCAL.remove();
    }
}
