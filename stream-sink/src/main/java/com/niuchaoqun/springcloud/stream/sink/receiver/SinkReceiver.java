package com.niuchaoqun.springcloud.stream.sink.receiver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

/**
 * 用来指定一个或多个定义了 @Input 或 @Output 注解的接口
 *
 * @author niuchaoqun
 */
@EnableBinding(Sink.class)
public class SinkReceiver {
    private static final Logger logger = LoggerFactory.getLogger(SinkReceiver.class);

    @StreamListener(Sink.INPUT)
    public void receive(String payload) {
        logger.info("Received: " + payload);
    }
}
