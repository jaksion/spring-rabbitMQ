package com.twy.directconsumer.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 自连型交换机：消费者
 * 消费者单纯的使用，其实可以不用添加这个配置，直接建后面的监听就好，使用注解来让监听器监听对应的队列即可。
 * 但是配置上了这个配置的话，其实消费者也是生成者的身份，也能推送该消息。
 * @Author twy
 * @CreateTime 2020/11/02
 */
@Configuration
public class DirectRabbitMqConfig {

    /**
     * 测试自连型交换机：TestDirectExchange
     * @return
     */
    @Bean
    public DirectExchange TestDirectExchange(){
        return new DirectExchange("TestDirectExchange",true,false);
    }

    /**
     * 测试队列：TestDirectQueue
     * @return
     */
    @Bean
    public Queue TestDirectQueue(){
        //一般我们设置一下队列的持久化就好,其余两个就是默认false
        return new Queue("TestDirectQueue",true);
    }

    /**
     * 将队列和交换机绑定, 并设置用于匹配键：TestDirectBinding
     * @return
     */
    @Bean
    public Binding TestDirectBinding(){
        return BindingBuilder.bind(TestDirectQueue()).to(TestDirectExchange()).with("TestDirectBinding");
    }

}
