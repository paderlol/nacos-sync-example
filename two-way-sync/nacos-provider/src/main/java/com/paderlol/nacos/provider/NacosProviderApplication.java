package com.paderlol.nacos.provider;

import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author  paderlol
 */
@SpringBootApplication
@Slf4j
@EnableDubbo
public class NacosProviderApplication {


    public static void main(String[] args) {
        SpringApplication.run(NacosProviderApplication.class);
    }


}
