package com.niuchaoqun.springcloud.eureka.consumer.controller;

import com.niuchaoqun.springcloud.commons.rest.RestJson;
import com.niuchaoqun.springcloud.commons.rest.RestResponse;
import com.niuchaoqun.springcloud.commons.rest.RestResult;
import com.niuchaoqun.springcloud.eureka.consumer.entity.User;
import com.niuchaoqun.springcloud.eureka.consumer.feign.EurekaClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author niuchaoqun
 */
@RestController
@RequestMapping("/feign")
public class FeignController {
    private static final Logger logger = LoggerFactory.getLogger(FeignController.class);

    @Autowired
    private EurekaClient eurekaClient;

    @RequestMapping("/get_param")
    public String getParam(String param1, String param2) {
        logger.info(param1);
        logger.info(param2);

        long start = System.currentTimeMillis();
        String result = eurekaClient.getParam(param1, param2);
        logger.info("{}ms, result:{}", System.currentTimeMillis() - start, result);

        return result;
    }

    @RequestMapping("/get_object")
    public String getObject(User user) {
        logger.info(user.toString());

        String result = eurekaClient.getObject(user);

        return result;
    }

    @RequestMapping("/get_path/{path}")
    public String getPath(@PathVariable(value = "path") String path, @RequestParam(value = "param") String param) {
        logger.info(path);
        logger.info(param);

        String result = eurekaClient.getPath(path, param);
        return result;
    }

    @PostMapping("/post_object")
    public String postObject(User user) {
        logger.info(user.toString());
        String result = eurekaClient.postObject(user);
        return result;
    }

    @PostMapping("/post_form")
    public String postForm(User user) {
        logger.info(user.toString());
        String result = eurekaClient.postForm(user);
        return result;
    }

    @PostMapping("/post_param")
    public String postParam(String param1, String param2) {
        logger.info(param1);
        logger.info(param2);

        String result = eurekaClient.postParam(param1, param2);
        return result;
    }

    @PostMapping("/post_mixparam")
    public String postMixParam(User user, String param2) {
        logger.info(user.toString());
        logger.info(param2);

        String result = eurekaClient.postMixParam(user, param2);
        return result;
    }

    @RequestMapping("/get")
    public String get() {
        long start = System.currentTimeMillis();
        String result = eurekaClient.get();
        logger.info("{}ms, result:{}", System.currentTimeMillis() - start, result);

        return result;
    }

    @RequestMapping("/get_sleep")
    public String getSleep() {
        String result = eurekaClient.getSleep();

        return result;
    }

    @RequestMapping("/remove")
    public RestResult remove() {
        RestJson result = eurekaClient.remove();
        return RestResponse.data(result);
    }
}