package com.linjiasong.article.mq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author linjiasong
 * @date 2025/1/21 上午10:46
 */
@Configuration
public class RabbitMQTopicConfig {
    public static final String TOPIC_EXCHANGE_USER_ARTICLE = "user-article-exchange";
    public static final String TOPIC_QUEUE_USER_ARTICLE = "user-article-queue";
    public static final String TOPIC_USER_ARTICLE = "topic.user.article";

    public static final String TOPIC_EXCHANGE_ADMIN_ARTICLE = "admin-article-exchange";
    public static final String TOPIC_QUEUE_ADMIN_ARTICLE = "admin-article-queue";
    public static final String TOPIC_ADMIN_ARTICLE = "topic.admin.article";

    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(TOPIC_EXCHANGE_USER_ARTICLE);
    }

    @Bean
    public Queue topicQueueUserArticle() {
        return new Queue(TOPIC_QUEUE_USER_ARTICLE);
    }

    @Bean
    public Binding bindingTopicQueue1(Queue topicQueueUserArticle, TopicExchange topicExchange) {
        return BindingBuilder.bind(topicQueueUserArticle).to(topicExchange).with(TOPIC_USER_ARTICLE);
    }

    @Bean
    public TopicExchange adminArticleExchange() {
        return new TopicExchange(TOPIC_EXCHANGE_ADMIN_ARTICLE);
    }

    @Bean
    public Queue adminArticleQueue() {
        return new Queue(TOPIC_QUEUE_ADMIN_ARTICLE);
    }

    @Bean
    public Binding bindingAdminArticleQueue(Queue adminArticleQueue, TopicExchange adminArticleExchange) {
        return BindingBuilder.bind(adminArticleQueue).to(adminArticleExchange).with(TOPIC_ADMIN_ARTICLE);
    }
}
