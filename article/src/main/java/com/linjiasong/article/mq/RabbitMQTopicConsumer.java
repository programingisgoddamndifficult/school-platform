package com.linjiasong.article.mq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author linjiasong
 * @date 2025/1/21 上午10:45
 */
@Component
public class RabbitMQTopicConsumer {

    @RabbitListener(queues = RabbitMQTopicConfig.TOPIC_QUEUE_USER_ARTICLE)
    public void receiveMessageFromTopicQueue1(String message) {
        System.out.println("Received from user-article-queue: " + message);
    }

}
