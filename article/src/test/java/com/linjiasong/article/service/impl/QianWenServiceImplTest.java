package com.linjiasong.article.service.impl;

import com.linjiasong.article.entity.dto.QianWenChatDTO;
import com.linjiasong.article.service.QianWenService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author linjiasong
 * @date 2025/2/13 上午11:42
 */
@SpringBootTest
class QianWenServiceImplTest {

    @Autowired
    QianWenService qianWenService;

    @Test
    void testChat(){
        QianWenChatDTO qianWenChatDTO = new QianWenChatDTO();
        qianWenChatDTO.setContent("你是谁");
        System.out.println(qianWenService.chat(qianWenChatDTO));
    }

}