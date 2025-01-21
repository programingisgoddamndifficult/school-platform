package com.linjiasong.user.mq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author linjiasong
 * @date 2025/1/21 上午10:42
 */
@Component
public class RabbitMQProducer {

    @Autowired
    private AmqpTemplate amqpTemplate;

    public void sendMessage(String exchange,String routingKey, String message) {
        amqpTemplate.convertAndSend(exchange, routingKey, message);
    }

}
