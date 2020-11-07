package com.twy.fanoutconsumer.listener;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RabbitListener(queues = "fanout.queue.B")
public class FanoutConsumerQueueB {
    @RabbitHandler
    public void process(Map message) {
        System.out.println("FanoutConsumerQueueB消费者监听队列B,收到消息  : " + message.toString());
    }
}