package com.io.ssm.module.mq.receive;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * 消息侦听器：监听当前的Message Queue
 * 从Queue中读取消息，并输出到当前控制台中
 */
public class MessageReceiver implements MessageListener {

    public void onMessage(Message message) {

        if (message instanceof TextMessage){

            TextMessage textMessage = (TextMessage) message;
            try {

                String text = textMessage.getText();
                System.out.println(String.format("Received: %s",text));
                try {

                    Thread.sleep(100);
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
            } catch (JMSException e){
                e.printStackTrace();
            }
        }
    }
}
