package com.lh.mq.activemq.spring.producer;

import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;
import org.springframework.context.ApplicationContext;

/**
 * Created by LH 2446059046@qq.com on 2017/7/14.
 */
public class SpringProducer {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("producer.xml");
        ProducerService service = context.getBean(ProducerServiceImpl.class);

        for (int i = 0; i < 100; i++) {
            service.sendMessage("spring test" + i);
        }

    }
}
