package com.io.boot.module.toIndex;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by 小五儿 on 2017-08-16
 */
@Controller
public class ToIndexController {

    @RequestMapping("/")
    public String toIndex() {
        System.out.println("这是跳转到首页controller。。。。。。。");
        return "index";
    }
}
