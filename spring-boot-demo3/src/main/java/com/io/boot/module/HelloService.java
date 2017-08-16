package com.io.boot.module;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class HelloService {

    @Autowired
    private HelloDao helloDao;

    public List<Map> listGetAll(){
        return helloDao.listGetAll();
    }

}
