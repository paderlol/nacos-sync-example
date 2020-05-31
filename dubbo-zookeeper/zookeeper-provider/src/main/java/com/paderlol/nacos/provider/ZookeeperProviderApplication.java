package com.paderlol.nacos.provider;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author  paderlol
 */
@SpringBootApplication
@EnableDubbo
public class ZookeeperProviderApplication {



    public static void main(String[] args) {
        SpringApplication.run(ZookeeperProviderApplication.class);
    }


}
