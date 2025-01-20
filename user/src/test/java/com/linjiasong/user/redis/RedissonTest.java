package com.linjiasong.user.redis;

import org.junit.jupiter.api.Test;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author linjiasong
 * @date 2025/1/20 下午3:10
 */
@SpringBootTest
public class RedissonTest {

    @Autowired
    RedissonClient redissonClient;

    @Test
    void testSetRedisson(){
        RBucket<Object> bucket = redissonClient.getBucket("ljs");
        bucket.set("ljs");
    }

    @Test
    void testGetKey(){
        RBucket<Object> bucket = redissonClient.getBucket("ljs");
        Object o = bucket.get();
        System.out.println(o);
    }

}
