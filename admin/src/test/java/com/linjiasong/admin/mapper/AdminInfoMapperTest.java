package com.linjiasong.admin.mapper;

import com.linjiasong.admin.entity.AdminInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author linjiasong
 * @date 2025/1/17 下午2:31
 */

@SpringBootTest
class AdminInfoMapperTest {

    @Autowired
    AdminInfoMapper adminInfoMapper;

    @Test
    void testInsert(){
        int insert = adminInfoMapper.insert(AdminInfo.builder().username("ljs").password("ljs").build());
        System.out.println(insert);
    }

}