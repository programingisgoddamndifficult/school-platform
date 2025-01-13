package com.linjiasong.user.mapper;

import com.linjiasong.user.entity.UserInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author linjiasong
 * @date 2025/1/13 下午4:23
 */

@SpringBootTest
class UserInfoMapperTest {

    @Autowired
    UserInfoMapper userInfoMapper;

    @Test
    void testInsert(){
        int insert = userInfoMapper.insert(UserInfo.builder()
                .username("test1")
                .password("123456")
                .phone("17722657389")
                .email("test1@qq.com")
                .build());
        System.out.println(insert);
    }

    @Test
    void testGet(){
        UserInfo userInfo = userInfoMapper.selectById(1);
        if(userInfo == null){
            throw new RuntimeException();
        }
        System.out.println(userInfo);
    }

    @Test
    void testUpdate(){
        UserInfo userInfo = userInfoMapper.selectById(1);
        userInfo.setPassword("1234567");
        int i = userInfoMapper.updateById(userInfo);
        System.out.println(i);
    }

    @Test
    void testDelete(){
        int i = userInfoMapper.deleteById(1);
        System.out.println(i);
    }

}