package com.example.controller;

import static com.example.NacosCloudConsulConsumerApplication.HelloService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author paderlol
 * @date: 2019-04-06 21:51
 */
@RestController
public class HelloController {

    @Autowired
    private HelloService helloService;

    @GetMapping(value = "/consul-hello")
    public String hello() {
        return helloService.hello();
    }


    @GetMapping(value = "/hello")
    public String hello2() {
        return "Hello World from Nacos!";
    }

}
