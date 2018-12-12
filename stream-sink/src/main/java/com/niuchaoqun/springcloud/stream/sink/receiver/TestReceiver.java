package com.niuchaoqun.springcloud.stream.sink.receiver;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface TestReceiver {
    String INPUT = "input";

    @Input(TestReceiver.INPUT)
    SubscribableChannel input();
}
