package com.niuchaoqun.springcloud.eureka.consumer.dto.json;

import com.niuchaoqun.springcloud.commons.rest.RestJson;
import com.niuchaoqun.springcloud.eureka.consumer.entity.User;
import com.niuchaoqun.springcloud.eureka.consumer.entity.UserDetail;
import lombok.Data;

@Data
public class RecordJson extends RestJson {
    private DataJson data;

    @Data
    public static class DataJson {
        private User user;
        private UserDetail detial;
    }
}
