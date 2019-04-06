package com.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author paderlol
 * @date: 2019-04-06 18:08
 */
@RestController
public class HelloController {
    @GetMapping(value = "/hello")
    public String hello() {

        return "Hello World from Eureka!";
    }
}
