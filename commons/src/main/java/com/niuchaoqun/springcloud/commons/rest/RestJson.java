package com.niuchaoqun.springcloud.commons.rest;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class RestJson {
    protected Boolean state;

    protected String message;

    protected String error;
}
