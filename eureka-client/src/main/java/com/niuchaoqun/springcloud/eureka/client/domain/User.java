package com.niuchaoqun.springcloud.eureka.client.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {
    private Long id;

    private String name;
}
