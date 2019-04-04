package com.paderlol.nacos.consumer;

import com.alibaba.dubbo.config.annotation.Reference;
import com.paderlol.nacos.api.DemoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author  paderlol
 */
@SpringBootApplication
@Slf4j
public class ZookeeperConsumerApplication implements ApplicationRunner {

    @Reference(version = "${demo.service.version}",group = "${demo.service.group}")
    private DemoService nacosDemoService;

    public static void main(String[] args) {
        SpringApplication.run(ZookeeperConsumerApplication.class).close();
    }

    public void run(ApplicationArguments args) throws Exception {
        log.info(nacosDemoService.sayHello("This is from Nacos to Zookeeper Sync !"));
    }
}
