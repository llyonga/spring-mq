package com.io.ssm.module.dubbo.user;


import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

/**
 * Created by 小五儿 on 2017-07-28
 */
//@Path("user")
public class UserServiceImpl implements UserService {

    /*@GET
    @Path("sayHello")
    @Consumes({MediaType.APPLICATION_JSON})*/
    @Override
    public String sayHello(String name) {
        System.out.println("*************这个是sayHello**************");
        return "name is:" + name;
    }
}
