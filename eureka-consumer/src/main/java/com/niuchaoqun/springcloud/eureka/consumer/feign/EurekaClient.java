package com.niuchaoqun.springcloud.eureka.consumer.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("eureka-client")
public interface EurekaClient {

    @GetMapping("/get")
    String get();
}
