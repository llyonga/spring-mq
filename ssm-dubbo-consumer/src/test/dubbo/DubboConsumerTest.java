package dubbo;

import com.io.ssm.module.dubbo.city.CityService;
import com.io.ssm.module.dubbo.user.UserService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by 小五儿 on 2017-07-28
 */
public class DubboConsumerTest {

    @Test
    public void method() throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/spring-dubbo-consumer.xml");
        UserService userService = (UserService) context.getBean("userService");
        System.out.println(userService.sayHello("zhangsan"));


        System.out.println("*********************************");

       /* CityService cityService = (CityService) context.getBean("cityService");
        System.out.println(cityService.getCity());*/

        System.in.read();

    }


}
