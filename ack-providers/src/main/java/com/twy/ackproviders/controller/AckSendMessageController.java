package com.twy.ackproviders.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
public class AckSendMessageController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * （1）消息推送到server，但是在server里找不到交换机
     *
     * @return
     */
    @PostMapping("/TestMessageAck1")
    public String TestMessageAck1() {
        String messageId = String.valueOf(UUID.randomUUID());
        String messageData = "AckMessage: 消息推送到server，但是在server里找不到交换机!!!";
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Map<String, Object> map = new HashMap<>();
        map.put("messageId", messageId);
        map.put("messageData", messageData);
        map.put("createTime", createTime);
        /**
         * 把消息推送到名为‘non-existent-exchange’的交换机上（这个交换机是没有创建没有配置的）
         */
        rabbitTemplate.convertAndSend("non-existent-exchange", "TestDirectRouting", map);
        return "ok";
    }


    /**
     * （2）消息推送到server，找到交换机了，但是没找到队列
     *
     * @return
     */
    @PostMapping("/TestMessageAck2")
    public String TestMessageAck2() {
        String messageId = String.valueOf(UUID.randomUUID());
        String messageData = "AckMessage: 消息推送到server，找到交换机了，但是没找到队列!!!";
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Map<String, Object> map = new HashMap<>();
        map.put("messageId", messageId);
        map.put("messageData", messageData);
        map.put("createTime", createTime);
        /**
         * 把消息推送到名为‘noBindingQueueExchange’的交换机上（这个交换机是没有绑定队列）
         */
        rabbitTemplate.convertAndSend("noBindingQueueExchange", "TestDirectRouting", map);
        return "ok";
    }

    /**
     * （3）推送消息成功
     */
    @PostMapping("/TestMessageAck3")
    public String TestMessageAck3() {
        String messageId = String.valueOf(UUID.randomUUID());
        String messageData = "AckMessage: 推送消息成功!!!";
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Map<String, Object> map = new HashMap<>();
        map.put("messageId", messageId);
        map.put("messageData", messageData);
        map.put("createTime", createTime);
        /**
         * 把消息推送到名为‘TestDirectExchange’的交换机上（这个交换机是绑定队列）
         * BindingBuilder.bind(TestDirectQueue()).to(TestDirectExchange()).with("TestDirectBinding");
         */
        rabbitTemplate.convertAndSend("TestDirectExchange", "TestDirectBinding", map);
        return "ok";
    }
}
