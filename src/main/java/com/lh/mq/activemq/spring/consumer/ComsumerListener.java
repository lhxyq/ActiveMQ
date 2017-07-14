package com.lh.mq.activemq.spring.consumer;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * Created by LH 2446059046@qq.com on 2017/7/14.
 */
public class ComsumerListener implements MessageListener{
    @Override
    public void onMessage(Message message) {
        TextMessage textMessage = (TextMessage) message;
        try {
            System.out.println("接受消息" + textMessage.getText());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
