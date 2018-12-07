package com.niuchaoqun.springcloud.eureka.consumer.domain;

import lombok.Data;

@Data
public class Record  {
    private User user;
    private UserDetail detial;

    @Data
    public static class User {
        private Long id;
        private String name;
    }

    @Data
    public static class UserDetail {
        private String address;
    }
}
