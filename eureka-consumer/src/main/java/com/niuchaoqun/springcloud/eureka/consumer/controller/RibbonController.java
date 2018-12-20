package com.niuchaoqun.springcloud.eureka.consumer.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.niuchaoqun.springcloud.commons.rest.RestJson;
import com.niuchaoqun.springcloud.commons.rest.RestResponse;
import com.niuchaoqun.springcloud.commons.rest.RestResult;
import com.niuchaoqun.springcloud.eureka.consumer.dto.json.RecordJson;
import com.niuchaoqun.springcloud.eureka.consumer.dto.rest.RecordRest;
import com.niuchaoqun.springcloud.eureka.consumer.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.HashMap;

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
    public String getParam(@PathVariable String serviceId, @RequestParam String param1, @RequestParam String param2) {
        logger.info(param1);
        String result = restTemplate.getForObject("http://" + serviceId + "/get_param?param1={1}&param2={2}", String.class, param1, param2);

        ObjectMapper mapper = new ObjectMapper();
        RestResult restResult = null;
        try {
            restResult = mapper.readValue(result, RestResult.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        logger.info(restResult.toString());

        return result;
    }

    @RequestMapping("/get_object/{serviceId}")
    public String getOjbect(@PathVariable String serviceId, User user) {
        logger.info(user.toString());

        String result = restTemplate.getForObject("http://" + serviceId + "/get_object?id={1}&name={2}", String.class, user.getId(), user.getName());

        return result;
    }

    @RequestMapping("/post_object/{serviceId}")
    public String postObject(@PathVariable String serviceId, User user) {
        logger.info(user.toString());
        String result = restTemplate.postForObject("http://" + serviceId + "/post_object", user, String.class);
        return result;
    }

    @RequestMapping("/post_form/{serviceId}")
    public String postForm(@PathVariable String serviceId, User user) {
        logger.info(user.toString());

        LinkedMultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        map.add("id", user.getId());
        map.add("name", user.getName());

        String result = restTemplate.postForObject("http://" + serviceId + "/post_form", user, String.class);
        return result;
    }

    @RequestMapping("/post_param/{serviceId}")
    public String postParam(@PathVariable String serviceId, String param1, String param2) {
        logger.info(param1);
        logger.info(param2);

        LinkedMultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        map.add("param1", param1);
        map.add("param2", param2);

        String result = restTemplate.postForObject("http://" + serviceId + "/post_param", map, String.class);
        return result;
    }

    @RequestMapping("/post_mixparam/{serviceId}")
    public String postMixParam(@PathVariable String serviceId, User user, String param2) {
        logger.info(user.toString());
        logger.info(param2);

        String result = restTemplate.postForObject("http://" + serviceId + "/post_mixparam", user, String.class);
        return result;
    }

    @RequestMapping("/get_record/{serviceId}")
    public RestResult<RecordRest> getRecord(@PathVariable String serviceId) {
        RecordJson recordJson = restTemplate.getForObject("http://" + serviceId + "/get_record", RecordJson.class);
        logger.info(recordJson.toString());

        RecordRest recordRest = RecordRest.builder()
                .user(recordJson.getData().getUser())
                .detial(recordJson.getData().getDetial())
                .build();

        return RestResponse.data(recordRest);
    }

    @RequestMapping("/remove/{serviceId}")
    public RestResult remove(@PathVariable String serviceId) {
        ResponseEntity<RestJson> exchange = restTemplate.exchange("http://" + serviceId + "/remove",
                HttpMethod.DELETE,
                new HttpEntity<>(null, null),
                RestJson.class);
        if (exchange.getStatusCodeValue() == 200) {
            RestJson rest = exchange.getBody();
            if (rest != null && rest.getState()) {
                return RestResponse.success(rest.getMessage());
            }
        }

        return RestResponse.fail();
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
