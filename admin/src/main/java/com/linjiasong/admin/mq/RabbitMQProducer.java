package com.linjiasong.admin.mq;

import com.linjiasong.admin.mq.dto.MqBaseExchangeDTO;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author linjiasong
 * @date 2025/1/23 下午6:03
 */
@Component
public class RabbitMQProducer {

    @Autowired
    private AmqpTemplate amqpTemplate;

    public void sendMessage(String exchange, String routingKey, MqBaseExchangeDTO mqBaseExchangeDTO) {
        amqpTemplate.convertAndSend(exchange, routingKey, mqBaseExchangeDTO, message -> {
            message.getMessageProperties().setDeliveryMode(MessageDeliveryMode.PERSISTENT);
            return message;
        });
    }

}
