package kr.ant.kpa.pharmcrew.config;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import com.bumdori.spring.BLogger;
import com.bumdori.spring.config.HostAndPort;
import com.bumdori.util.StringUtils;

import io.lettuce.core.ReadFrom;
import kr.ant.kpa.pharmcrew.Config;
import lombok.Getter;

@Configuration
public class RedisConfig {
    private final Logger logger = LoggerFactory.getLogger(RedisConfig.class);

    @Getter
    public enum RedisServer {
//        LOCAL(null, HostAndPort.builder().host("localhost").port(10100).build()),
//        LOCAL_API_SESSION(null, HostAndPort.builder().host("localhost").port(10101).build()),
//        AMYS(null, HostAndPort.builder().host("amytech.cafe24.com").port(10100).build()),
//        AMYS_API_SESSION(null, HostAndPort.builder().host("amytech.cafe24.com").port(10101).build()),
//        AMYS_ADMIN_SESSION(null, HostAndPort.builder().host("amytech.cafe24.com").port(10102).build()),
//        AMYS(null, HostAndPort.builder().host("52.207.107.241").port(10100).build()),
        AMYS(null, HostAndPort.builder().host("localhost").port(10100).build()),
        AMYS_API_SESSION(null, HostAndPort.builder().host("localhost").port(10101).build()),
        AMYS_ADMIN_SESSION(null, HostAndPort.builder().host("localhost").port(10102).build()),

        PROD(null, HostAndPort.builder().host("localhost").port(10100).build()),
        PROD_API_SESSION(null, HostAndPort.builder().host("localhost").port(10101).build()),
        PROD_ADMIN_SESSION(null, HostAndPort.builder().host("localhost").port(10102).build()),
        ;

        private String password;
        private HostAndPort hostAndPorts[];

        RedisServer(String password, HostAndPort ... hostAndPorts) {
            this.password = password;
            this.hostAndPorts = hostAndPorts;
        }

        public RedisConnectionFactory connectionFactory() {
            if (hostAndPorts.length == 1) {
                HostAndPort hostAndPort = hostAndPorts[0];
                LettuceClientConfiguration clientConfiguration = LettuceClientConfiguration.builder()
                        .readFrom(ReadFrom.REPLICA_PREFERRED)
                        .build();
                RedisStandaloneConfiguration serverConfiguration = new RedisStandaloneConfiguration(hostAndPort.getHost(), hostAndPort.getPort());
                if (!StringUtils.isEmpty(password)) serverConfiguration.setPassword(password);
                return new LettuceConnectionFactory(serverConfiguration, clientConfiguration);
            } else {
                RedisSentinelConfiguration configuration = new RedisSentinelConfiguration()
                        .master("MASTER");
                for (HostAndPort hostAndPort: hostAndPorts) {
                    configuration.sentinel(hostAndPort.getHost(), hostAndPort.getPort());
                }
                if (!StringUtils.isEmpty(password)) configuration.setPassword(password);
                return new LettuceConnectionFactory(configuration);
            }
        }
    }

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        RedisServer server = Config.Inst.redisServer();
        RedisConnectionFactory connectionFactory = server.connectionFactory();
        BLogger.info(logger, "redisConnectionFactory: {}", connectionFactory);
        return connectionFactory;
    }

    @Bean
    public <K extends Serializable, V extends Serializable> RedisTemplate<K, V> redisTemplate() {
        RedisConnectionFactory redisConnectionFactory = redisConnectionFactory();
        RedisTemplate<K, V> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        return template;
    }
}
