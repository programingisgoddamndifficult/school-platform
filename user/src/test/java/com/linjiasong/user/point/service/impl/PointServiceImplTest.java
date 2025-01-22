package com.linjiasong.user.point.service.impl;

import com.linjiasong.user.point.dto.PointArticleDTO;
import com.linjiasong.user.point.service.PointService;
import com.linjiasong.user.point.enums.PointTypeEnum;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
        pointService.execute(PointTypeEnum.POINT_ARTICLE, PointArticleDTO.builder().articleId(213L).build());
    }

}