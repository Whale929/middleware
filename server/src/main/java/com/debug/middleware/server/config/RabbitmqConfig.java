package com.debug.middleware.server.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class RabbitmqConfig {
    private static final Logger log = LoggerFactory.getLogger(RabbitmqConfig.class);

    @Autowired
    private CachingConnectionFactory connectionFactory;

    @Autowired
    private SimpleRabbitListenerContainerFactoryConfigurer factoryConfigurer;

    @Bean(name = "singleListenerContainer")
    public SimpleRabbitListenerContainerFactory factory() {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(new Jackson2JsonMessageConverter());
        factory.setMaxConcurrentConsumers(1);
        factory.setPrefetchCount(1);
        return factory;
    }

    @Bean(name = "multiListenerContainer")
    public SimpleRabbitListenerContainerFactory multiListenerCOntainer() {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factoryConfigurer.configure(factory, connectionFactory);
        factory.setMessageConverter(new Jackson2JsonMessageConverter());
        factory.setAcknowledgeMode(AcknowledgeMode.NONE);
        factory.setMaxConcurrentConsumers(15);
        factory.setConcurrentConsumers(10);
        factory.setPrefetchCount(10);
        return factory;
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
        connectionFactory.setPublisherConfirms(true);
        connectionFactory.setPublisherReturns(true);
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMandatory(true);
        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
            @Override
            public void confirm(CorrelationData correlationData, boolean b, String s) {
                log.info("消息发送成功:correlationData({}),ack({}),cause({})",correlationData,b,s);
            }
        });
        rabbitTemplate.setReturnCallback(new RabbitTemplate.ReturnCallback() {
            @Override
            public void returnedMessage(Message message, int i, String s, String s1, String s2) {
                log.info("消息丢失:exchange({}),route({}),replyCode({}),replyText({}),message{}",s1,s2,i,s,message);
            }
        });
        return rabbitTemplate;
    }

    //定义读取配置文件的环境变量实例
    @Autowired
    private Environment env;

    /**创建简单消息模型：队列、交换机和路由 **/

    //创建队列
    @Bean(name = "basicQueue")
    public Queue basicQueue(){
        return new Queue(env.getProperty("mq.basic.info.queue.name"),true);
    }

    //创建交换机：在这里以DirectExchange为例，在后面章节中我们将继续详细介绍这种消息模型
    @Bean
    public DirectExchange basicExchange(){
        return new DirectExchange(env.getProperty("mq.basic.info.exchange.name"),true,false);
    }

    //创建绑定
    @Bean
    public Binding basicBinding(){
        return BindingBuilder.bind(basicQueue()).to(basicExchange()).with(env.getProperty("mq.basic.info.routing.key.name"));
    }

    /**
     *  objectQueue
     * @return
     */
    @Bean(name = "objectQueue")
    public Queue objectQueue(){
        return new Queue(env.getProperty("mq.object.info.queue.name"),true);
    }

    @Bean
    public DirectExchange objectExchange(){
        return new DirectExchange(env.getProperty("mq.object.info.exchange.name"),true,false);
    }

    @Bean
    public Binding objectBinding(){
        return BindingBuilder.bind(objectQueue()).to(objectExchange()).with(env.getProperty("mq.object.info.routing.key.name"));
    }

    /**
     *  fanoutExchange模型
     *  多个queue，一个exchange，多个binding
     */
    @Bean(name = "fanoutQueueOne")
    public Queue fanoutQueueOne(){
        return new Queue(env.getProperty("mq.fanout.queue.one.name"),true);
    }

    @Bean(name = "fanoutQueueTwo")
    public Queue fanoutQueueTwo(){
        return new Queue(env.getProperty("mq.fanout.queue.two.name"),true);
    }

    @Bean
    public FanoutExchange fanoutExchange(){
        return new FanoutExchange(env.getProperty("mq.fanout.exchange.name"),true,false);
    }

    @Bean
    public Binding fanoutBindingOne(){
        return BindingBuilder.bind(fanoutQueueOne()).to(fanoutExchange());
    }

    @Bean
    public Binding fanoutBindingTwo(){
        return BindingBuilder.bind(fanoutQueueTwo()).to(fanoutExchange());
    }
}
