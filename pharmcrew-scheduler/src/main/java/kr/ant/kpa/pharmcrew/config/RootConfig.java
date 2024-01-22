package kr.ant.kpa.pharmcrew.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.bumdori.spring.BLogger;

@Configuration
@ComponentScan(
        basePackages = {
                "kr.ant.kpa.pharmcrew",
        }
)
public class RootConfig {
    private final Logger logger = LoggerFactory.getLogger(RootConfig.class);

    public RootConfig() {
        BLogger.info(logger, "constructor: {}", this);
    }
}
