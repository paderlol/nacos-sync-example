package com.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.example.NacosConsumerApplication.HelloService;

/**
 * @author paderlol
 * @date: 2019-04-06 21:51
 */
@RestController
public class HelloController {
    private final HelloService helloService;

    public HelloController(HelloService helloService) {
        this.helloService = helloService;
    }

    @GetMapping(value = "/hello")
    public String hello(){
        return helloService.hello();
    }
}
