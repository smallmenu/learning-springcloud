package com.niuchaoqun.springcloud.commons.rest;

import lombok.Data;

@Data
public class RestObject {
    private Boolean state;

    private String message;

    private String error;
}
