package com.twy.topicconsumer.listener;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 只要是消息携带的路由键是以topic.开头,都会分发到该队列
 */
@Component
@RabbitListener(queues = "TestSecondQueue")
public class TopicSecondQueueListener {
    @RabbitHandler
    public void process(Map message) {
        System.out.println("Topic Second Queue Listener 消费者收到消息  : " + message.toString());
    }
}
