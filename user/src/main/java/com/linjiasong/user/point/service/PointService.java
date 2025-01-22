package com.linjiasong.user.point.service;

import org.springframework.stereotype.Service;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;

/**
 * @author linjiasong
 * @date 2025/1/22 上午10:24
 */
public interface PointService {

    /**
     * 提交Runnable任务
     * @param task task
     */
    void execute(Runnable task);

    /**
     * 提交Callable任务并获取Future
     * @param task task
     * @return <T>
     * @param <T> <T>
     */
    <T> Future<T> submit(Callable<T> task);
}
