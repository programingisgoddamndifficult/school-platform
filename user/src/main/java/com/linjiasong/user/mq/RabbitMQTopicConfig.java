package com.linjiasong.user.mq;

import org.springframework.context.annotation.Configuration;

/**
 * @author linjiasong
 * @date 2025/1/21 上午10:47
 */
@Configuration
public class RabbitMQTopicConfig {
    public static final String TOPIC_EXCHANGE_USER_ARTICLE = "user-article-exchange";
    public static final String TOPIC_QUEUE_USER_ARTICLE = "user-article-queue";
    public static final String TOPIC_USER_ARTICLE = "topic.user.article";
}
