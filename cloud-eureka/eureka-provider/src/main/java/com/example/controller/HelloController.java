package com.example.controller;

import com.example.EurekaProviderApplication.HelloService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author paderlol
 * @date: 2019-04-06 18:08
 */
@RestController
public class HelloController {

    private final HelloService helloService;

    public HelloController(HelloService helloService) {
        this.helloService = helloService;
    }

    @GetMapping(value = "/hello")
    public String hello() {

        return "Hello World from Eureka!";
    }

    @GetMapping(value = "/hello-nacos")
    public String hello2() {

        return helloService.hello();
    }
}
