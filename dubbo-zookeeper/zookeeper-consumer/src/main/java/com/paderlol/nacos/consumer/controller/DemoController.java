package com.paderlol.nacos.consumer.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.paderlol.nacos.api.DemoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author paderlol
 * @date: 2019-04-11 22:01
 */
@RestController
public class DemoController {
    @Reference(version = "${demo.service.version}", group = "${demo.service.group}")
    private DemoService nacosDemoService;

    @GetMapping("/sayHello")
    public String sayHello() {
        return nacosDemoService.sayHello("This is from Nacos to Zookeeper Sync !");

    }
}
