package com.niuchaoqun.springcloud.eureka.client.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Record {
    private User user;
    private UserDetail detial;
}
