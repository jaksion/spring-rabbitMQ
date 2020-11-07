package com.twy.fanoutprovider.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 扇形交换机配置文件
 *
 */

@Configuration
public class FanoutRabbitMqConfig {


    /**
     * 测试扇形交换机
     * @return
     */
    @Bean
    public FanoutExchange TestFanoutExchange(){
        return new FanoutExchange("TestFanoutExchange");
    }

    /**
     * 队列A
     * @return
     */
    @Bean
    public Queue queueA(){
        return new Queue("fanout.queue.A");
    }

    /**
     * 队列B
     * @return
     */
    @Bean
    public Queue queueB(){
        return new Queue("fanout.queue.B");
    }

    /**
     * 队列C
     * @return
     */
    @Bean
    public Queue queueC(){
        return new Queue("fanout.queue.C");
    }

    /**
     * 队列A绑定扇形交换机
     */
    @Bean
    public Binding queueABindingExchange(){
        return BindingBuilder.bind(queueA()).to(TestFanoutExchange());
    }

    /**
     * 队列B绑定扇形交换机
     */
    @Bean
    public Binding queueBBindingExchange(){
        return BindingBuilder.bind(queueB()).to(TestFanoutExchange());
    }

    /**
     * 队列C绑定扇形交换机
     */
    @Bean
    public Binding queueCBindingExchange(){
        return BindingBuilder.bind(queueC()).to(TestFanoutExchange());
    }
}
