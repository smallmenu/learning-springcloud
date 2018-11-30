package com.niuchaoqun.springcloud.eureka.consumer.feign;

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
}
