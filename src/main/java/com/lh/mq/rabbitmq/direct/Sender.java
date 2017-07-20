package com.lh.mq.rabbitmq.direct;


import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

/**
 * Created by LH 2446059046@qq.com on 2017/7/20.
 */
public class Sender {
//    private static final String QUEUE_NAME = "user";
    private static final String EXCHANGE_NAME = "topic_user";

    public static void main(String[] args) throws Exception{
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection con = factory.newConnection();
        Channel channel = con.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, "topic");

//        channel.queueDeclare(QUEUE_NAME, true, false, false, null);
        for (int i = 0; i < 100; i++) {
            String message = "hello world!\t" + i;
            channel.basicPublish(EXCHANGE_NAME, "user.add", MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes());

            System.out.println("{X}Sent:" + message +"");
        }

        channel.close();
        con.close();
    }
}
