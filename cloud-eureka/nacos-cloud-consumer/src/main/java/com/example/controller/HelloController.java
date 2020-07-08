package com.example.controller;

import static com.example.NacosConsumerApplication.HelloService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping(value = "/hello-eureka")
    public String hello(){
        return helloService.hello();
    }

    @GetMapping(value = "/hello")
    public String hello2() {

        return "Hello World from Nacos!";
    }
}
