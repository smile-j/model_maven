package com.dong.base.test.mq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.junit.Test;

/**
 * @author dongjunpeng
 * @Description
 * @date 2021/6/21
 */
public class Provider {


    @Test
    public void sendMeg() throws Exception{
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("127.0.0.1");
        connectionFactory.setHost("5672");
        connectionFactory.setVirtualHost("/");
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");

        //获取连接对象
        Connection connection = connectionFactory.newConnection();
        //获取通道
        Channel channel = connection.createChannel();

        //通过绑定对应的消息队列
        //参数1.队列的名字，如果不存在，自动创建
        //参数2.durable 队列是否持久化
        //参数3.exclusive 是否独占队列
        //参数4.autoDelete 是否消费完成后自动删除
        //参数5.额外的
        channel.queueDeclare("hello",false,false,false,null);

        //发布消息
        //参数1. 交换机
        //参数2.队列名称
        //参数3.传递消息额外设置
        //参数4.消息具体的内容
        channel.basicPublish("","hello",null,"hello rabbmitmq!".getBytes());

        channel.close();
        connection.close();

    }

}
