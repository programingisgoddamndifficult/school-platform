package com.linjiasong.user.mq;

import com.linjiasong.user.mq.config.RabbitMQTopicConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author linjiasong
 * @date 2025/1/21 上午10:53
 */
@SpringBootTest
class RabbitMQProducerTest {

    @Autowired
    RabbitMQProducer rabbitMQProducer;

    @Test
    void testSendMessage() {
        rabbitMQProducer.sendMessage(RabbitMQTopicConfig.TOPIC_EXCHANGE_USER_ARTICLE, RabbitMQTopicConfig.TOPIC_USER_ARTICLE, null);
    }

}