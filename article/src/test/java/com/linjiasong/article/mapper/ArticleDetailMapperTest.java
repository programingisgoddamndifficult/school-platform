package com.linjiasong.article.mapper;

import com.alibaba.fastjson.JSON;
import com.linjiasong.article.entity.ArticleDetail;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author linjiasong
 * @date 2025/1/15 下午5:19
 */

@SpringBootTest
class ArticleDetailMapperTest {

    @Autowired
    ArticleDetailMapper articleDetailMapper;

    @Test
    void testInsert(){
        int insert = articleDetailMapper.insert(ArticleDetail.builder()
                .articleId(1L)
                .content("1")
                .imageUrl(JSON.toJSONString(ArticleDetail.ImageUrl.builder()
                        .imageUrls(List.of("123"))
                        .build()))
                .build());
        System.out.println(insert);
    }

    @Test
    void testGet(){
        ArticleDetail articleDetail = articleDetailMapper.selectById(1);
        String imageUrl = articleDetail.getImageUrl();
        ArticleDetail.ImageUrl imageUrl1 = JSON.parseObject(imageUrl, ArticleDetail.ImageUrl.class);
        imageUrl1.getImageUrls().forEach(System.out::println);
        System.out.println(articleDetail);


        System.out.println(ArticleDetail.getImageUrls(imageUrl));
    }

}