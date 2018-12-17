package com.niuchaoqun.springcloud.eureka.consumer.dto.json;

import com.niuchaoqun.springcloud.commons.rest.RestBean;
import com.niuchaoqun.springcloud.eureka.consumer.entity.User;
import com.niuchaoqun.springcloud.eureka.consumer.entity.UserDetail;
import lombok.Builder;
import lombok.Data;

@Data
public class RestJsonRecord extends RestBean {
    private DataBean data;

    @Data
    public static class DataBean {
        private User user;
        private UserDetail detial;
    }
}
