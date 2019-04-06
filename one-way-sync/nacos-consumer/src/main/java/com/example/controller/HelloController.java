package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.example.NacosConsumerApplication.HelloService;

/**
 * @author paderlol
 * @date: 2019-04-06 21:51
 */
@RestController
public class HelloController {
    @Autowired
    private HelloService helloService;

    @GetMapping(value = "/nacos-hello")
    public String hello(){
        return helloService.hello();
    }
}
