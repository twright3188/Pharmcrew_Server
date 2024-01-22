package kr.ant.kpa.pharmcrew.sms;

import kr.ant.kpa.pharmcrew.sms.exception.SmsException;
import kr.ant.kpa.pharmcrew.sms.impl.SmsSenderImpl;
import org.junit.After;
import org.junit.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.Assert.*;

public class SmsSenderTest {
    private final ConfigurableApplicationContext context;
    private final SmsSender smsSender;

    public SmsSenderTest() {
        context = new AnnotationConfigApplicationContext(
                SmsSenderImpl.class
        );
        smsSender = context.getBean(SmsSender.class);
    }

//    @Test
    public void send() throws SmsException {
        smsSender.send("01025433283", "test");
    }

    @After
    public void close() {
        context.close();
    }

}