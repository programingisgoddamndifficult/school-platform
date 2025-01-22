package com.linjiasong.user.point.service.impl;

import com.linjiasong.user.point.config.PointConfig;
import com.linjiasong.user.point.service.PointService;
import org.springframework.stereotype.Service;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;

/**
 * @author linjiasong
 * @date 2025/1/22 上午10:26
 */
@Service
public class PointServiceImpl implements PointService {
    @Override
    public void execute(Runnable task) {
        PointConfig.execute(task);
    }

    @Override
    public <T> Future<T> submit(Callable<T> task) {
        return PointConfig.submit(task);
    }
}
