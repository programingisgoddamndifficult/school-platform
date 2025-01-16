package com.linjiasong.article.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.linjiasong.article.entity.ArticleBasicInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author linjiasong
 * @date 2025/01/16 22:10
 */

@SpringBootTest
public class ArticleBasicInfoMapperTest {

    @Autowired
    ArticleBasicInfoMapper articleBasicInfoMapper;

    @Test
    void testGetByUserId(){
        List<ArticleBasicInfo> basicInfoList = articleBasicInfoMapper.selectList(new QueryWrapper<ArticleBasicInfo>()
                .eq("user_id", 14));
        basicInfoList.forEach(System.out::println);
    }

}
