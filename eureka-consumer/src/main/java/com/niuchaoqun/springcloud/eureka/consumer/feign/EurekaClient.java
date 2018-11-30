package com.niuchaoqun.springcloud.eureka.consumer.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "eureka-client", fallback = EurekaClientFallback.class)
public interface EurekaClient {

    @GetMapping("/get")
    String get();

    @GetMapping("/get_param")
    String getParam(@RequestParam(value = "param1", required = false) String param1);

    @GetMapping("/get_sleep")
    String getSleep();
}
