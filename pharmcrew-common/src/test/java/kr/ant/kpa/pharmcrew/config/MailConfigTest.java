package kr.ant.kpa.pharmcrew.config;

import org.junit.After;
import org.junit.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;

import javax.mail.internet.MimeMessage;

public class MailConfigTest {
    private final ConfigurableApplicationContext context;
//    private final MailSender mailSender;
    private final JavaMailSender mailSender;

    public MailConfigTest() {
        context = new AnnotationConfigApplicationContext(
                MailConfig.class
        );
        mailSender = context.getBean(JavaMailSender.class);
    }

//    @Test
    public void send() {
        MimeMessagePreparator preparator = new MimeMessagePreparator() {

            @Override
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
                helper.setFrom("팜크루 <no-reply@ant-soft.com>");
                helper.setTo("최동혁 <donghyouk@ckeic.com>");
                helper.setSubject("제목");
                helper.setText("내용", true);
            }
        };
        mailSender.send(preparator);
    }

    @After
    public void close() {
        context.close();
    }
}