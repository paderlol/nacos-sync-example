package com.paderlol.nacos.provider.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.paderlol.nacos.api.DemoService;
import org.springframework.beans.factory.annotation.Value;

/**
 * @author paderlol
 */
@Service(version = "${demo.service.version}", group = "${demo.service.group}")
public class ZookeeperDemoServiceImpl implements DemoService {


    @Value("${spring.application.name}")
    private String applicationName;

    public String sayHello(String name) {
        return String.format("[%s] : Hello, %s", applicationName, name);
    }
}
