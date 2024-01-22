package kr.ant.kpa.pharmcrew.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;

import com.bumdori.spring.BLogger;
import com.google.gson.Gson;

@Configuration
@ComponentScan(
        basePackages = {
                "kr.ant.kpa.pharmcrew",
        },
        excludeFilters = {
                @ComponentScan.Filter(type = FilterType.ANNOTATION, value = {
                        Controller.class,
                }),
                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = WebMvcConfig.class),
        }
)
public class RootConfig {
    private final Logger logger = LoggerFactory.getLogger(RootConfig.class);

    public RootConfig() {
        BLogger.info(logger, "constructor: {}", this);
    }

    @Bean
    public Gson gson() {
        return new Gson();
    }
}
