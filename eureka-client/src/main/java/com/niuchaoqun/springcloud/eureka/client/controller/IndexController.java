package com.niuchaoqun.springcloud.eureka.client.controller;

import com.niuchaoqun.springcloud.eureka.client.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author niuchaoqun
 */
@RestController
public class IndexController {
    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

    @RequestMapping("/")
    public String index() {
        return "hello spring cloud eureka-client";
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public Object get() {
        return Response.success();
    }

    @RequestMapping(value = "/get_param", method = RequestMethod.GET)
    public Object getParam(@RequestParam(value = "param1", required = false) String param1) {
        logger.info(param1);
        if (param1 != null) {
            return Response.data(param1);
        }
        return Response.success();
    }

    @RequestMapping(value = "/post_param", method = RequestMethod.POST)
    public Object postParam(@RequestParam(value = "param1", required = false) String param1) {
        logger.info(param1);
        if (param1 != null) {
            return Response.data(param1);
        }
        return Response.success();
    }
}
