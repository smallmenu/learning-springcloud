package com.niuchaoqun.springcloud.eureka.consumer.dto.rest;

import com.niuchaoqun.springcloud.eureka.consumer.entity.User;
import com.niuchaoqun.springcloud.eureka.consumer.entity.UserDetail;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RecordRest {
    private User user;
    private UserDetail detial;
}
