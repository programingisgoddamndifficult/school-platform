package com.linjiasong.user.service;

import com.linjiasong.user.entity.UserInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author linjiasong
 * @date 2025/1/13 下午4:35
 */

@SpringBootTest
class UserInfoServiceTest {

    @Autowired
    UserInfoService userInfoService;

    @Test
    void testInsert(){
        boolean test1 = userInfoService.save(UserInfo.builder()
                .username("test2")
                .password("123456")
                .phone("17722657388")
                .email("test2@qq.com")
                .build());
        System.out.println(test1);
    }
}