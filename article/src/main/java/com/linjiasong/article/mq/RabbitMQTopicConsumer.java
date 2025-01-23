package com.linjiasong.article.mq;

import com.alibaba.fastjson.JSON;
import com.linjiasong.article.config.SpringContext;
import com.linjiasong.article.mq.config.RabbitMQTopicConfig;
import com.linjiasong.article.mq.dto.MqBaseExchangeDTO;
import org.springframework.amqp.core.Message;
import com.linjiasong.article.mq.enums.MqExchangeTypeEnum;
import com.linjiasong.article.mq.service.AbstractMqService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import com.rabbitmq.client.Channel;

import java.io.IOException;

/**
 * @author linjiasong
 * @date 2025/1/21 上午10:45
 */
@Component
@Slf4j
public class RabbitMQTopicConsumer {

    @RabbitListener(queues = RabbitMQTopicConfig.TOPIC_QUEUE_USER_ARTICLE, ackMode = "MANUAL")
    public void userArticleTopicQueue(MqBaseExchangeDTO mqBaseExchangeDTO, Channel channel, Message message) {
        AbstractMqService mqService = getMqService(RabbitMQTopicConfig.TOPIC_QUEUE_USER_ARTICLE, mqBaseExchangeDTO);
        doConsume(mqService, mqBaseExchangeDTO, channel, message);
    }

    @RabbitListener(queues = RabbitMQTopicConfig.TOPIC_QUEUE_ADMIN_ARTICLE, ackMode = "MANUAL")
    public void articleCheckTopicQueue(MqBaseExchangeDTO mqBaseExchangeDTO, Channel channel, Message message) {
        AbstractMqService mqService = getMqService(RabbitMQTopicConfig.TOPIC_EXCHANGE_ADMIN_ARTICLE, mqBaseExchangeDTO);
        doConsume(mqService, mqBaseExchangeDTO, channel, message);
    }

    private AbstractMqService getMqService(String msgType, MqBaseExchangeDTO mqBaseExchangeDTO) {
        log.info(String.format("队列: %s 收到消息 [%s] 准备执行消费", msgType,
                JSON.toJSONString(mqBaseExchangeDTO)));
        MqExchangeTypeEnum exchangeType = mqBaseExchangeDTO.getExchangeType();
        return SpringContext.getBean(exchangeType.getType(), AbstractMqService.class);
    }

    private void doConsume(AbstractMqService mqService, MqBaseExchangeDTO mqBaseExchangeDTO,
                           Channel channel, Message message) {
        try {
            mqService.consume(mqBaseExchangeDTO.getJsonData());
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            try {
                channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
            } catch (IOException ex) {
                log.error("消息拒绝时发生异常: ", ex);
            }
        }
    }

}
