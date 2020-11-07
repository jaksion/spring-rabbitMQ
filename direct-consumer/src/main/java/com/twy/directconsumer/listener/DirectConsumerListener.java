package com.twy.directconsumer.listener;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 消费者消息接收监听类
 */
@Component
@RabbitListener(queues = "TestDirectQueue") //监听的队列名称，之前在配置文件中设置的
public class DirectConsumerListener {

    @RabbitHandler
    public void receive(Map message){
        System.out.println("Direct Consumer 监听到的消息：" + message.toString()
        );
    }
}
