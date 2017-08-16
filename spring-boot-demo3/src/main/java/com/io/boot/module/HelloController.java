package com.io.boot.module;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;


@Controller
public class HelloController {

    @Autowired
    private HelloService helloService;

    @RequestMapping("hello")
    public String helloJsp(ModelMap model){
        System.out.println("HelloController 执行了。。。。。。");
        model.addAttribute("hello", "你好啊，springboot！");
        List<Map> list = helloService.listGetAll();
        model.addAttribute("listMap",list);
        System.out.println("返回页面。。。。。。");
        return "demo/hello";
    }

}
