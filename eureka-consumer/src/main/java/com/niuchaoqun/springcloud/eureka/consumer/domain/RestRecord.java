package com.niuchaoqun.springcloud.eureka.consumer.domain;

import com.niuchaoqun.springcloud.commons.rest.RestObject;
import lombok.Data;

@Data
public class RestRecord extends RestObject {
    private Record data;
}
