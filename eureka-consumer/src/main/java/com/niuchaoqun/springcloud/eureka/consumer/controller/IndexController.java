package com.niuchaoqun.springcloud.eureka.consumer.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author niuchaoqun
 */
@RestController
public class IndexController {
    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    DiscoveryClient discoveryClient;

    @RequestMapping("/")
    public String index() {
        return "Hello Spring Cloud, Eureka Consumer";
    }

    @RequestMapping("/services")
    public List<String> services() {
        List<String> services = discoveryClient.getServices();
        logger.info(services.toString());
        return services;
    }

    @RequestMapping("/instance/{serviceId}")
    public List<ServiceInstance> instances(@PathVariable String serviceId) {
        List<ServiceInstance> instances = discoveryClient.getInstances(serviceId);
        logger.info(instances.toString());
        return instances;
    }
}
