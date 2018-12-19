package com.niuchaoqun.springcloud.eureka.consumer.feign;

import com.niuchaoqun.springcloud.commons.rest.RestJson;
import org.springframework.stereotype.Component;

@Component
public class EurekaClientFallback implements EurekaClient {
    @Override
    public String get() {
        return null;
    }

    @Override
    public String getParam(String param1) {
        return null;
    }

    @Override
    public String getSleep() {
        return "fallback";
    }

    @Override
    public RestJson remove() {
        return null;
    }
}
