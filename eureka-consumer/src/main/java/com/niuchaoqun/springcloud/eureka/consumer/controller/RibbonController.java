package com.niuchaoqun.springcloud.eureka.consumer.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.niuchaoqun.springcloud.commons.rest.RestBean;
import com.niuchaoqun.springcloud.commons.rest.RestResponse;
import com.niuchaoqun.springcloud.commons.rest.RestResult;
import com.niuchaoqun.springcloud.eureka.consumer.dto.json.RestJsonRecord;
import com.niuchaoqun.springcloud.eureka.consumer.dto.rest.RestRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

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
        logger.info(param1);
        String result = restTemplate.getForObject("http://" + serviceId + "/get_param?param1={1}", String.class, param1);

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

    @RequestMapping("/get_record/{serviceId}")
    public RestResult<RestRecord> getRecord(@PathVariable String serviceId) {
        RestJsonRecord restJsonRecord = restTemplate.getForObject("http://" + serviceId + "/get_record", RestJsonRecord.class);
        logger.info(restJsonRecord.toString());

        RestRecord restRecord = RestRecord.builder()
                .user(restJsonRecord.getData().getUser())
                .detial(restJsonRecord.getData().getDetial())
                .build();

        return RestResponse.data(restRecord);
    }

    @RequestMapping("/remove/{serviceId}")
    public RestResult remove(@PathVariable String serviceId) {
        ResponseEntity<RestBean> exchange = restTemplate.exchange("http://" + serviceId + "/remove",
                HttpMethod.DELETE,
                new HttpEntity<>(null, null),
                RestBean.class);
        if (exchange.getStatusCodeValue() == 200) {
            RestBean rest = exchange.getBody();
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
