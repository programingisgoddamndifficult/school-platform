package com.linjiasong.article.mq;

import com.alibaba.fastjson.JSON;
import com.linjiasong.article.config.SpringContext;
import com.linjiasong.article.mq.config.RabbitMQTopicConfig;
import com.linjiasong.article.mq.dto.MqBaseExchangeDTO;
import com.linjiasong.article.mq.enums.MqExchangeTypeEnum;
import com.linjiasong.article.mq.service.AbstractMqService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author linjiasong
 * @date 2025/1/21 上午10:45
 */
@Component
@Slf4j
public class RabbitMQTopicConsumer {

    @RabbitListener(queues = RabbitMQTopicConfig.TOPIC_QUEUE_USER_ARTICLE)
    public void userArticleTopicQueue(MqBaseExchangeDTO mqBaseExchangeDTO) {
        log.info(String.format("队列: %s 收到消息 [%s] 准备执行消费", RabbitMQTopicConfig.TOPIC_QUEUE_USER_ARTICLE,
                JSON.toJSONString(mqBaseExchangeDTO)));
        MqExchangeTypeEnum exchangeType = mqBaseExchangeDTO.getExchangeType();
        AbstractMqService mqService = SpringContext.getBean(exchangeType.getType(), AbstractMqService.class);
        mqService.consume(mqBaseExchangeDTO.getJsonData());
    }

}
