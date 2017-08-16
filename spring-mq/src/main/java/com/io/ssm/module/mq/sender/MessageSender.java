package com.io.ssm.module.mq.sender;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

/**
 * 发送消息的服务：发送字符串信息
 */

@Component
public class MessageSender {

    private final JmsTemplate jmsTemplate;

    public MessageSender(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void send(final String text) {
        jmsTemplate.convertAndSend(text);
        System.out.println("send: " + text);
    }

}
