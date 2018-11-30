package com.niuchaoqun.springcloud.eureka.consumer.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Ribbon
 *
 * @author niuchaoqun
 */
@RequestMapping("/ribbon")
@RestController
public class RibbonController {
    private static final Logger logger = LoggerFactory.getLogger(RibbonController.class);

    @Autowired
    RestTemplate restTemplate;

    @RequestMapping("{serviceId}")
    public String index(@PathVariable String serviceId) {
        long start = System.currentTimeMillis();
        String result = restTemplate.getForObject("http://" + serviceId, String.class);
        logger.info("{}ms, result:{}", System.currentTimeMillis() - start, result);

        return result;
    }

    @RequestMapping("/get/{serviceId}")
    public String get(@PathVariable String serviceId) {
        String result = restTemplate.getForObject("http://" + serviceId + "/get", String.class);
        logger.info(result);

        return result;
    }

    @RequestMapping("/get_param/{serviceId}")
    public String getParam(@PathVariable String serviceId, @RequestParam String param1) {
        String result = restTemplate.getForObject("http://" + serviceId + "/get_param?param1={1}", String.class, param1);
        logger.info(result);

        return result;
    }

    @RequestMapping("/get_sleep/{serviceId}")
    @HystrixCommand(fallbackMethod = "fallback")
    public String getSleep(@PathVariable String serviceId) {
        String result = restTemplate.getForObject("http://" + serviceId + "/get_sleep", String.class);

        return result;
    }

    public String fallback(String serviceId) {
        return "fallback";
    }
}
