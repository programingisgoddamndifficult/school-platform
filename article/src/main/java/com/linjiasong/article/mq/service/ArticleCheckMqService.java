package com.linjiasong.article.mq.service;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.linjiasong.article.entity.ArticleBasicInfo;
import com.linjiasong.article.excepiton.BizException;
import com.linjiasong.article.mq.dto.ArticleMqCheckDTO;
import com.linjiasong.article.mq.dto.UserDeleteDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author linjiasong
 * @date 2025/1/23 下午6:46
 */
@Service("ARTICLE_CHECK")
@Slf4j
public class ArticleCheckMqService extends AbstractMqService {
    @Override
    public void consume(String mqData) {
        ArticleMqCheckDTO articleMqCheckDTO = JSON.parseObject(mqData, ArticleMqCheckDTO.class);
        boolean update = articleBasicInfoGateway.update(new UpdateWrapper<ArticleBasicInfo>()
                .eq("id", articleMqCheckDTO.getArticleId())
                .set("is_check", 1)
                .set("is_ban", articleMqCheckDTO.isDoBan() ? 1 : 0));
        if(!update){
            log.info(String.format("ARTICLE_CHECK 消费MQ消息失败 mqData:%s", mqData));
            throw new BizException(String.format("ARTICLE_CHECK 消费MQ消息失败 mqData:%s", mqData));
        }

        log.info(String.format("ARTICLE_CHECK 消费MQ消息成功 mqData:%s", mqData));
    }
}
