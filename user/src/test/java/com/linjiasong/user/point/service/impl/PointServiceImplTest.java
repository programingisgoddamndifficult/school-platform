package com.linjiasong.user.point.service.impl;

import com.linjiasong.user.point.service.PointService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author linjiasong
 * @date 2025/1/22 上午10:28
 */
@SpringBootTest
class PointServiceImplTest {

    @Autowired
    private PointService pointService;

    @Test
    void testExecute(){
        pointService.execute(()->{
            System.out.println("线程池测试成功1");
        });
        pointService.execute(()->{
            System.out.println("线程池测试成功2");
        });
        pointService.execute(()->{
            System.out.println("线程池测试成功3");
        });
        pointService.execute(()->{
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("线程池测试成功3");
        });
    }

}