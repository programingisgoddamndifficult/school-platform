package com.linjiasong.article.gateway.impl;

import com.linjiasong.article.gateway.ArticleBasicInfoGateway;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author linjiasong
 * @date 2025/01/16 22:08
 */

@SpringBootTest
class ArticleBasicInfoGatewayImplTest {

    @Autowired
    ArticleBasicInfoGateway articleBasicInfoGateway;

    @Test
    void testDeleteById(){
        boolean b = articleBasicInfoGateway.deleteById(7L);
        System.out.println(b);
    }

}