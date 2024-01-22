package kr.ant.kpa.pharmcrew.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

import com.bumdori.spring.BLogger;

import kr.ant.kpa.pharmcrew.Config;

@EnableRedisHttpSession
public class RedisSessionConfig {
    private final Logger logger = LoggerFactory.getLogger(RedisSessionConfig.class);

    public RedisSessionConfig() {
        BLogger.info(logger, "constructor: {}", this);
    }

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        RedisConfig.RedisServer server = Config.Inst.redisForApiSessionServer();
        RedisConnectionFactory connectionFactory = server.connectionFactory();
        BLogger.info(logger, "redisConnectionFactory: {}", connectionFactory);
        return connectionFactory;
    }
}
