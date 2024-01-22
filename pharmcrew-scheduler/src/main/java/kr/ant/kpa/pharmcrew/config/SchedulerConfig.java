package kr.ant.kpa.pharmcrew.config;

import kr.ant.kpa.pharmcrew.scheduler.PushScheduler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@Configuration
public class SchedulerConfig {

    @Bean
    public PushScheduler pushScheduler() {
        return new PushScheduler();
    }
}
