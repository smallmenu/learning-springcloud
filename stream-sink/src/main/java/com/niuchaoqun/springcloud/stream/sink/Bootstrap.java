package com.niuchaoqun.springcloud.stream.sink;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Component;

@EnableBinding(value = {Bootstrap.SinkSender.class})
@Component
public class Bootstrap implements CommandLineRunner {

    @Autowired
    private SinkSender sinkSender;

    @Override
    public void run(String... args) throws Exception {
        for (int i = 0; i < 10; i++) {
            sinkSender.output().send(MessageBuilder.withPayload("asdf" + i).build());

            Thread.sleep(2000);
        }
    }

    public interface SinkSender {

        String OUTPUT = "output";

        @Output(SinkSender.OUTPUT)
        MessageChannel output();
    }
}
