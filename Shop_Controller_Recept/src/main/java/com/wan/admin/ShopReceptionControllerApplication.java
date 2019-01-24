package com.wan.admin;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(scanBasePackages = "com.wan")
public class ShopReceptionControllerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopReceptionControllerApplication.class, args);
    }


    /**
     * 声明队列
     * @return
     */
    @Bean
    public Queue getQueue(){
        return new Queue("Goods_Queue");
    }

    /**
     * 声明交换机
     * @return
     */
    @Bean
    public FanoutExchange getExchange(){
        return new FanoutExchange("Goods_Exchange");
    }

    /**
     * 绑定交换机和队列
     * @param getQueue
     * @param getExchange
     * @return
     */
    @Bean
    public Binding getExchangeBinding(Queue getQueue, FanoutExchange getExchange){
        return BindingBuilder.bind(getQueue).to(getExchange);
    }



}

