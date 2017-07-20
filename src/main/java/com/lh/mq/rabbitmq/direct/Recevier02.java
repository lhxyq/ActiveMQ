package com.lh.mq.rabbitmq.direct;

import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * Created by LH 2446059046@qq.com on 2017/7/20.
 */
public class Recevier02 {
    private static final String QUEUE_NAME = "person";
    private static final String EXCHANGE_NAME = "topic_user";

    public static void main(String[] args) throws Exception{
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection con = factory.newConnection();
        Channel channel = con.createChannel();
        channel.exchangeDeclare(EXCHANGE_NAME, "topic");

        channel.queueDeclare(QUEUE_NAME, true, false, false, null);
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "user.*");
        System.out.println("{*} Waiting for message" );
        channel.basicQos(1);

        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {

                }
                System.out.println("{x}received:" + message + "");
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        };

        channel.basicConsume(QUEUE_NAME, false, consumer);

    }
}
