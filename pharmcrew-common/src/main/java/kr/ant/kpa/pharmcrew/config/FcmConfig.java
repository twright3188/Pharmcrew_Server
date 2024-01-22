package kr.ant.kpa.pharmcrew.config;

import java.io.IOException;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.bumdori.spring.BLogger;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import kr.ant.kpa.pharmcrew.fcm.FcmSender;

@Configuration
public class FcmConfig {
    private final Logger logger = LoggerFactory.getLogger(FcmConfig.class);

    @Bean
    public FcmSender fcmSender() {
        return new FcmSender();
    }

    public FcmConfig() {
        BLogger.info(logger, "constructor: {}", this);
        try {
            InputStream serviceAccount = new ClassPathResource(
//                    "kr-ant-kpa-pharmcrew-firebase-adminsdk-qtyhh-a570f449b3.json"
                    "pharmcrew-be0f6-9c1cb92627d7.json"
            ).getInputStream();
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://kr-ant-kpa-pharmcrew.firebaseio.com")
                    .build();
            FirebaseApp.initializeApp(options);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
