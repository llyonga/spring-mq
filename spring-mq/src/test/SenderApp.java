
import com.io.ssm.module.mq.sender.MessageSender;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 发送消息到ActiveMQ的Message Queue
 */
public class SenderApp {

    public static void main(String[] args) throws IOException {

        start("classpath:spring/spring-jms.xml");
    }

    public static void start(String configLocation) throws IOException {

        MessageSender sender = getMessageSender(configLocation);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Please input your message:");
        String text = br.readLine();
        while (!StringUtils.isEmpty(text)) {

            System.out.println(String.format("send message: %s", text));
            sender.send(text);
            text = br.readLine();
        }
    }

    public static MessageSender getMessageSender(String configLocation) {

        ApplicationContext context = new ClassPathXmlApplicationContext(configLocation);
        return (MessageSender) context.getBean("messageSender");
    }

}

