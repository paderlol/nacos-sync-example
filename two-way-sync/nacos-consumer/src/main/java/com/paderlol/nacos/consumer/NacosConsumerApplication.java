package com.paderlol.nacos.consumer;

import com.paderlol.nacos.api.DemoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author  paderlol
 */
@SpringBootApplication
@Slf4j
public class NacosConsumerApplication implements ApplicationRunner {

    @Reference(version = "${demo.service.version}",group = "${demo.service.group}")
    private DemoService zookeeperDemoService;

    public static void main(String[] args) {
        SpringApplication.run(NacosConsumerApplication.class).close();
    }

    public void run(ApplicationArguments args) throws Exception {
        log.info(zookeeperDemoService.sayHello("This is from Zookeeper to Nacos Sync !"));
    }
}
