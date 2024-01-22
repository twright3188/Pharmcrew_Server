package kr.ant.kpa.pharmcrew;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bumdori.spring.BLogger;
import com.bumdori.util.NetworkUtils;

import kr.ant.kpa.pharmcrew.config.DbConfig;
import kr.ant.kpa.pharmcrew.config.RedisConfig;

public enum Config {
    Inst;

    private static final Logger logger = LoggerFactory.getLogger(Config.class);

    private static final String user = System.getProperty("user.name");
    private static final String os = System.getProperty("os.name");
    private static final String host = NetworkUtils.getHost();
    private static final String ip = NetworkUtils.getIp();
    private static final String tmpDir = System.getProperty("java.io.tmpdir");

    private static Environment environment;

    static {
        BLogger.debug(logger, "user: {}", user);
        BLogger.debug(logger, "os: {}", os);
        BLogger.debug(logger, "host: {}", host);
        BLogger.debug(logger, "ip: {}", ip);
        BLogger.debug(logger, "tmpDir: {}", tmpDir);
        for (Environment value: Environment.values()) {
            if (value.check(user, os, host, ip, tmpDir)) {
                environment = value;
            }
        }

        if (environment == null) {
            throw new IllegalStateException("environment has not been set");
        }
        BLogger.info(logger, "environment: {}", environment);
    }

    // db
    public DbConfig.DbServer dbServer() {
        return environment.dbServer();
    }

    // reids
    public RedisConfig.RedisServer redisServer()  {
        return environment.redisServer();
    }
    public RedisConfig.RedisServer redisForApiSessionServer() {
        return environment.redisForApiSessionServer();
    }
    public RedisConfig.RedisServer redisForAdminSessionServer() {
        return environment.redisForAdminSessionServer();
    }

    // storage
    public String localStoragePath() {
        return environment.localStoragePath();
    }
    public String localStorageUrl() {
        return environment.localStorageUrl();
    }

}
