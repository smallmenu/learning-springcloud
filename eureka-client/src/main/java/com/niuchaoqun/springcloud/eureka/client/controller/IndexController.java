package com.niuchaoqun.springcloud.eureka.client.controller;

import com.niuchaoqun.springcloud.commons.rest.RestResponse;
import com.niuchaoqun.springcloud.commons.rest.RestResult;
import com.niuchaoqun.springcloud.eureka.client.domain.Record;
import com.niuchaoqun.springcloud.eureka.client.domain.User;
import com.niuchaoqun.springcloud.eureka.client.domain.UserDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

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
    public RestResult get() {
        return RestResponse.success();
    }

    @RequestMapping(value = "/get_param", method = RequestMethod.GET)
    public RestResult getParam(@RequestParam(value = "param1", required = false) String param1, @RequestParam(value = "param2", required = false) String param2) {
        logger.info(param1);
        logger.info(param2);

        return RestResponse.data(param1 + "/" + param2);
    }

    @RequestMapping(value = "/get_object", method = RequestMethod.GET)
    public RestResult getObject(User user) {
        logger.info(user.toString());

        return RestResponse.data(user);
    }

    @RequestMapping(value = "/post_object", method = RequestMethod.POST)
    public RestResult postObject(@RequestBody User user) {
        logger.info(user.toString());

        return RestResponse.data(user);
    }

    @RequestMapping(value = "/post_form", method = RequestMethod.POST)
    public RestResult postForm(User user) {
        logger.info(user.toString());

        return RestResponse.data(user);
    }

    @RequestMapping(value = "/post_param", method = RequestMethod.POST)
    public RestResult postParam(String param1, String param2) {
        logger.info(param1);
        logger.info(param2);

        return RestResponse.data(param1 + "/" + param2);
    }

    @RequestMapping(value = "/post_mixparam", method = RequestMethod.POST)
    public RestResult postMixParam(@RequestBody User user, String param2) {
        logger.info(user.toString());
        logger.info(param2);

        return RestResponse.data(user.toString() + "/" + param2);
    }

    @RequestMapping(value = "/get_record", method = RequestMethod.GET)
    public RestResult<Record> getRecord() {
        User user = User.builder().id(1L).name("索思").build();
        UserDetail detail = UserDetail.builder().address("上海徐汇区").build();
        Record record = Record.builder().user(user).detial(detail).build();
        return RestResponse.data(record);
    }

    @RequestMapping(value = "/remove", method = RequestMethod.DELETE)
    public RestResult remove() {
        return RestResponse.success("remove success");
    }

    @RequestMapping("/get_sleep")
    public RestResult getSleep() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return RestResponse.success();
    }
}
