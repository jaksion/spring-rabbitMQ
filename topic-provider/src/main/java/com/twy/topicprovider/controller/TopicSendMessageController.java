package com.twy.topicprovider.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 主题交换机消息推送测试
 */
@RestController
public class TopicSendMessageController {
    /**
     * 使用RabbitTemplate,提供了接收/发送等等方法
     */
    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 主题交换机测试接口
     * @param routingKey 绑定键值 topic.first
     * @param message 要发送的消息  主题交换机测试：绑定简直topic.first
     * @return
     */
    @RequestMapping("/sendTopicMessage")
    public String sendTopicMessage(@RequestParam("routingKey") String routingKey,
                                   @RequestParam("message") String message) {
        String messageId = String.valueOf(UUID.randomUUID());
        String messageData = message;
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Map<String,Object> map=new HashMap<>();
        map.put("messageId",messageId);
        map.put("messageData",messageData);
        map.put("createTime",createTime);
        //将消息推送到服务器
        //将消息携带绑定键值：routingKey 发送到交换机TestTopicExchange
        rabbitTemplate.convertAndSend("TestTopicExchange", routingKey, map);
        return "ok";
    }
}
