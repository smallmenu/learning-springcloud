package com.niuchaoqun.springcloud.eureka.consumer.feign;

import com.niuchaoqun.springcloud.commons.rest.RestJson;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "EUREKA-CLIENT", fallback = EurekaClientFallback.class)
public interface EurekaClient {

    @GetMapping("/get")
    String get();

    @GetMapping("/get_param")
    String getParam(@RequestParam(value = "param1", required = false) String param1);

    @GetMapping("/get_sleep")
    String getSleep();

    @DeleteMapping("/remove")
    RestJson remove();
}
