package com.twy.topicprovider.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 主题交换机配置类：生产者
 */

@Configuration
public class TopicRabbitMqConfig {
    /**
     * 定义绑定键
     */
    public final static String FirstRounting = "topic.first";
    public final static String SecondRounting = "topic.second";

    /**
     * 测试主题交换机
     * @return
     */
    @Bean
    public TopicExchange TestTopicExchange(){
        return new TopicExchange("TestTopicExchange");
    }

    /**
     * 主题测试队列一
     * @return
     */
    @Bean
    public Queue TestFirstQueue(){
        return new Queue("TestFirstQueue");
    }

    /**
     * 主题测试队列二
     * @return
     */
    @Bean
    public Queue TestSecondQueue(){
        return new Queue("TestSecondQueue");
    }

    /**
     * 测试队列一绑定主题交换机
     * 将TestFirstQueue和TestTopicExchange绑定,而且绑定的键值为topic.first
     * 这样只要是消息携带的路由键是topic.first,才会分发到该队列
     * @return
     */
    @Bean
    public Binding firstQueueBindingTopicExchange(){
        return BindingBuilder.bind(TestFirstQueue()).to(TestTopicExchange()).with(FirstRounting);
    }

    /**
     * 测试队列二绑定主题交换机
     * 将TestSecondQueue和TestTopicExchange绑定,而且绑定的键值为用上通配路由键规则topic.#
     * 这样只要是消息携带的路由键是以topic.开头,都会分发到该队列
     * @return
     */
    @Bean
    public Binding secondQueueBindingTopicExchange(){
        return BindingBuilder.bind(TestSecondQueue()).to(TestTopicExchange()).with("topic.#");
    }
}
