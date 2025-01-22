package com.linjiasong.user.point.config;

import java.util.concurrent.*;

/**
 * 埋点
 *
 * @author linjiasong
 * @date 2025/1/22 上午10:18
 */
public class PointConfig {

    public static final ThreadPoolExecutor POINT_THREAD_POOL_EXECUTOR = new ThreadPoolExecutor(10, 20, 10, TimeUnit.MINUTES, new LinkedBlockingQueue<Runnable>(), new ThreadPoolExecutor.AbortPolicy());

    /**
     * 提交Runnable任务
     * @param task task
     */
    public static void execute(Runnable task) {
        POINT_THREAD_POOL_EXECUTOR.execute(task);
    }

    /**
     * 提交Callable任务并获取Future
     * @param task task
     * @return <T>
     * @param <T> <T>
     */
    public static <T> Future<T> submit(Callable<T> task) {
        return POINT_THREAD_POOL_EXECUTOR.submit(task);
    }

}
