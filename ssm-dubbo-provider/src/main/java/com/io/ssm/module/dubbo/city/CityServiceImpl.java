package com.io.ssm.module.dubbo.city;

import org.springframework.web.bind.annotation.ResponseBody;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

/**
 * Created by 小五儿 on 2017-07-28
 */
@Path("city")
public class CityServiceImpl implements CityService {

    @GET
    @Path("getCity")
    @Consumes({MediaType.APPLICATION_JSON})
    //@ResponseBody
    @Override
    public String getCity() {
        System.out.println("方法执行了..........................");
        return "beijing";
    }
}
