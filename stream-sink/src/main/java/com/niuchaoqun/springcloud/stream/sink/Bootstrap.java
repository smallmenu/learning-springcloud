package com.niuchaoqun.springcloud.stream.sink;

import com.niuchaoqun.springcloud.stream.sink.sender.TestSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.stereotype.Component;

@EnableBinding(TestSender.class)
@Component
public class Bootstrap implements CommandLineRunner {

    @Autowired
    private TestSender testSender;

    @Override
    public void run(String... args) throws Exception {
        for (int i = 0; i < 4; i++) {
            testSender.output().send(MessageBuilder.withPayload("asdf" + i).build());

            Thread.sleep(2000);
        }
    }
}
