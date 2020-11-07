package com.twy.fanoutprovider.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 扇形交换机消息推送测试
 */
@RestController
public class FanoutSendMessageController {
    /**
     * 使用RabbitTemplate,提供了接收/发送等等方法
     */
    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 主题交换机测试接口
     * @return
     */
    @RequestMapping("/sendFanoutMessage")
    public String sendTopicMessage() {
        String messageId = String.valueOf(UUID.randomUUID());
        String messageData = "message: test FanoutExchange !!!";
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Map<String,Object> map=new HashMap<>();
        map.put("messageId",messageId);
        map.put("messageData",messageData);
        map.put("createTime",createTime);
        //将消息推送到服务器
        //将消息携带绑定键值：routingKey 发送到交换机TestFaoutExchange
        rabbitTemplate.convertAndSend("TestFanoutExchange", null, map);
        return "ok";
    }
}
