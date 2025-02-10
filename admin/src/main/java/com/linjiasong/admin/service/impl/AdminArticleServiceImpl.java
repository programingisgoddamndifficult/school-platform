package com.linjiasong.admin.service.impl;

import com.alibaba.fastjson.JSON;
import com.linjiasong.admin.config.RabbitMqConfig;
import com.linjiasong.admin.constant.RedisKeyEnum;
import com.linjiasong.admin.entity.dto.ArticleCheckDTO;
import com.linjiasong.admin.entity.vo.ArticleCheckVO;
import com.linjiasong.admin.excepiton.AdminBaseResponse;
import com.linjiasong.admin.excepiton.BizException;
import com.linjiasong.admin.mq.RabbitMQProducer;
import com.linjiasong.admin.mq.config.RabbitMQTopicConfig;
import com.linjiasong.admin.mq.dto.ArticleMqCheckDTO;
import com.linjiasong.admin.mq.dto.MqBaseExchangeDTO;
import com.linjiasong.admin.mq.enums.MqExchangeTypeEnum;
import com.linjiasong.admin.service.AdminArticleService;
import org.redisson.api.RBucket;
import org.redisson.api.RList;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author linjiasong
 * @date 2025/1/23 下午5:33
 */
@Service
public class AdminArticleServiceImpl implements AdminArticleService {

    @Autowired
    RedissonClient redissonClient;

    @Autowired
    RabbitMQProducer rabbitMQProducer;

    @Override
    public AdminBaseResponse getCheckArticleListFirst() {
        RList<String> list = redissonClient.getList(RedisKeyEnum.ARTICLE_CHECK_LIST.getKey());
        ArticleCheckVO articleCheckVO = JSON.parseObject(list.getFirst(), ArticleCheckVO.class);
        if (articleCheckVO == null) {
            throw new BizException("暂无新的文章待审核");
        }
        return AdminBaseResponse.success(articleCheckVO);
    }

    @Override
    public AdminBaseResponse checkArticle(ArticleCheckDTO articleCheckDTO) {
        checkArticleHasCheck(articleCheckDTO.getArticleId());

        removeArticleCheckList();

        rabbitMQProducer.sendMessage(RabbitMQTopicConfig.TOPIC_EXCHANGE_ADMIN_ARTICLE,
                RabbitMQTopicConfig.TOPIC_ADMIN_ARTICLE,
                MqBaseExchangeDTO.build(MqExchangeTypeEnum.ARTICLE_CHECK, ArticleMqCheckDTO.build(articleCheckDTO)));

        return AdminBaseResponse.success();
    }

    private void checkArticleHasCheck(Long articleId) {
        RBucket<Integer> bucket = redissonClient.getBucket(String.format(RedisKeyEnum.ARTICLE_DO_CHECK.getKey(), articleId));
        if (bucket.isExists()) {
            throw new BizException("当前文章已被审核");
        }

        bucket.set(1, RedisKeyEnum.ARTICLE_DO_CHECK.getExpiryTime(), RedisKeyEnum.ARTICLE_DO_CHECK.getTimeUnit());
    }

    private void removeArticleCheckList() {
        RList<ArticleCheckVO> list = redissonClient.getList(RedisKeyEnum.ARTICLE_CHECK_LIST.getKey());
        list.removeFirst();
    }
}
