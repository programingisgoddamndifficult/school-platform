package com.linjiasong.user.constant;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author linjiasong
 * @date 2025/02/23 16:19
 */
public class ThreadPoolContext {
    public static final ThreadPoolExecutor THREAD_POOL_EXECUTOR = new ThreadPoolExecutor(10, 20, 100, TimeUnit.MINUTES, new LinkedBlockingQueue<Runnable>(), new ThreadPoolExecutor.AbortPolicy());

    /**
     * 提交Runnable任务
     *
     * @param task task
     */
    public static void execute(Runnable task) {
        THREAD_POOL_EXECUTOR.execute(task);
    }
}
