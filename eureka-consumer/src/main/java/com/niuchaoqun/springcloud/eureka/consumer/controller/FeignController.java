package com.niuchaoqun.springcloud.eureka.consumer.controller;

import com.niuchaoqun.springcloud.eureka.consumer.feign.EurekaClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author niuchaoqun
 */
@RestController
@RequestMapping("/feign")
public class FeignController {
    private static final Logger logger = LoggerFactory.getLogger(FeignController.class);

    @Autowired
    EurekaClient eurekaClient;

    @RequestMapping("/get")
    public String get() {
        long start = System.currentTimeMillis();
        String result = eurekaClient.get();
        logger.info("{}ms, result:{}", System.currentTimeMillis() - start, result);

        return result;
    }

    @RequestMapping("/get_param")
    public String getParam(@RequestParam(value = "param1", required = false) String param1) {
        logger.info(param1);

        long start = System.currentTimeMillis();
        String result = eurekaClient.getParam(param1);
        logger.info("{}ms, result:{}", System.currentTimeMillis() - start, result);

        return result;
    }

    @RequestMapping("/get_sleep")
    public String getSleep() {
        String result = eurekaClient.getSleep();

        return result;
    }
}