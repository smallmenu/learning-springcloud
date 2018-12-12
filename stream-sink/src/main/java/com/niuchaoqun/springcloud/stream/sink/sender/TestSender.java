package com.niuchaoqun.springcloud.stream.sink.sender;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface TestSender {
    String OUTPUT = "output";

    @Output(TestSender.OUTPUT)
    MessageChannel output();
}
