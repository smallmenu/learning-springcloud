package com.niuchaoqun.springcloud.eureka.consumer.feign;

import com.niuchaoqun.springcloud.commons.rest.RestJson;
import com.niuchaoqun.springcloud.eureka.consumer.entity.User;
import org.springframework.stereotype.Component;

@Component
public class EurekaClientFallback implements EurekaClient {
    @Override
    public String get() {
        return null;
    }

    @Override
    public String getParam(String param1, String param2) {
        return null;
    }

    @Override
    public String getPath(String path, String param) {
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

    @Override
    public String postObject(User user) {
        return null;
    }

    @Override
    public String postParam(String param1, String param2) {
        return null;
    }

    @Override
    public String postMixParam(User user, String name) {
        return null;
    }

    @Override
    public String postForm(User user) {
        return null;
    }

    @Override
    public String getObject(User user) {
        return null;
    }
}
