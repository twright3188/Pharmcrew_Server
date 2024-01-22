package kr.ant.kpa.pharmcrew.fcm;

import com.bumdori.fcm.AbsFcmSender;
import kr.ant.kpa.pharmcrew.config.FcmConfig;
import org.junit.After;
import org.junit.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.Assert.*;

public class FcmSenderTest {
    private final ConfigurableApplicationContext context;
    private final FcmSender fcmSender;

    public FcmSenderTest() {
        context = new AnnotationConfigApplicationContext(
                FcmConfig.class
        );
        fcmSender = context.getBean(FcmSender.class);
    }

    // @Test
    public void send() {
        AbsFcmSender.FcmData data = new AbsFcmSender.FcmData();
        data.setTitle("test")
                .setBody("test")
                .setRegistrationToken("eEKUN4rYRri7uhVb9ZUgxm:APA91bEwNF6il28pw0HTqxRk5QUPlHVOREMNsB4rmIoQQ3y6Ne2_5N3vYDY5I-sjfQ0NEKxgw3n-sVb_8MvkJY-h7EuzPAnJg5v_YGV4i82gXaIBlahO9QO5PxlgEoWJv86fqELDT69U")
                ;
        fcmSender.send(data);
    }

    @After
    public void close() {
        context.close();
    }
}