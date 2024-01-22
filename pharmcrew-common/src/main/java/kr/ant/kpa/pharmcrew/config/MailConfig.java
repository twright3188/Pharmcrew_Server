package kr.ant.kpa.pharmcrew.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class MailConfig {
    private static final Logger logger = LoggerFactory.getLogger(MailConfig.class);

    // [REFRENCE] 메일 전송 https://offbyone.tistory.com/167
    @Bean
//    public MailSender mailSender() {
    public JavaMailSender mailSender() {
        JavaMailSenderImpl sender = new JavaMailSenderImpl();
        sender.setHost("smtps.hiworks.com");
        sender.setPort(465);
        sender.setUsername("no-reply@ant-soft.co.kr");
        sender.setPassword("1qazzaq1!!");
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", true);
        properties.put("mail.smtp.ssl.enable", true);
        sender.setJavaMailProperties(properties);
        return sender;
    }
}
