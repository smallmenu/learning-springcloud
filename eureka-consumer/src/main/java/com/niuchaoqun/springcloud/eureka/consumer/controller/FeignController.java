package com.niuchaoqun.springcloud.eureka.consumer.controller;

import com.niuchaoqun.springcloud.eureka.consumer.feign.EurekaClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/feign")
public class FeignController {
    private static final Logger logger = LoggerFactory.getLogger(FeignController.class);

    @Autowired
    EurekaClient eurekaClient;

    @RequestMapping("")
    public String index() {
        long start = System.currentTimeMillis();
        String result = eurekaClient.get();
        logger.info("{}ms, result:{}", System.currentTimeMillis() - start, result);

        return result;
    }
}
