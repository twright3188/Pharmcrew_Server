package kr.ant.kpa.pharmcrew.config;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.bumdori.spring.BLogger;
import com.zaxxer.hikari.HikariDataSource;

import kr.ant.kpa.pharmcrew.Config;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Configuration
@EnableTransactionManagement
public class DbConfig {
    private final Logger logger = LoggerFactory.getLogger(DbConfig.class);

    @Getter
    @AllArgsConstructor
    public enum DbServer {
//        LOCAL("localhost", 10000),
//        AMYS("amytech.cafe24.com"),
//    	AMYS("ant-mariadb.crbmdfnn8lkm.ap-northeast-2.rds.amazonaws.com", 3306, "pharmcrew", "pharmCrew", "#pharmCrew!!"),
    	AMYS("127.0.0.1", 3307, "kpapass", "root", ""),
//    	PROD("ant-mariadb.crbmdfnn8lkm.ap-northeast-2.rds.amazonaws.com", 3306, "pharmcrew", "pharmCrew", "#pharmCrew!!"),
    	PROD("211.253.9.245", 3306, "kpapass", "kpapass", "@kpapass!234&"),
        ;

        private String host;
        private int port = 3306;
        private String scheme = "pharmcrew";
        private String username = "pcdev";
        private String password = "dkagh123";
        private String option = "allowMultiQueries=true\nserverTimezone=Asia/Seoul";

        DbServer(String host) {
            this.host = host;
        }

        DbServer(String host, int port) {
            this.host = host;
            this.port = port;
        }
        
        DbServer(String host, int port, String scheme, String username, String password) {
            this.host = host;
            this.port = port;
            this.scheme = scheme;
            this.username = username;
            this.password = password;
        }
    }

    public DbConfig() {
        BLogger.info(logger, "constructor: {}", this);
    }

    @Bean(destroyMethod = "close")
    public HikariDataSource dataSource() {
        DbServer server = Config.Inst.dbServer();
        HikariDataSource dataSource = new HikariDataSource();
        
        //. mariaDB
        dataSource.setDriverClassName("org.mariadb.jdbc.Driver");
        dataSource.setJdbcUrl(String.format("jdbc:mariadb://%s:%d/%s", server.host, server.port, server.scheme));
        
        //. mysql
//        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
//        dataSource.setJdbcUrl(String.format("jdbc:mysql://%s:%d/%s", server.host, server.port, server.scheme));
        
        dataSource.setUsername(server.username);
        dataSource.setPassword(server.password);
        if (!StringUtils.isEmpty(server.option)) {
            try {
                Properties properties = new Properties();
                properties.load(new ByteArrayInputStream(server.option.getBytes(StandardCharsets.ISO_8859_1)));
                dataSource.setDataSourceProperties(properties);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        BLogger.info(logger, "dataSource: {}", dataSource);
        return dataSource;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(dataSource);
        Resource[] mapperLocations = new PathMatchingResourcePatternResolver().getResources("classpath*:kr/ant/kpa/pharmcrew/db/mapper/**/*.xml");
        BLogger.info(logger, "mapperLocations: {}", mapperLocations);
        sqlSessionFactory.setMapperLocations(
                mapperLocations
        );
        sqlSessionFactory.setTypeAliasesPackage("kr.ant.kpa.pharmcrew.db.vo");
        SqlSessionFactory sqlSessionFactoryObj = sqlSessionFactory.getObject();
        BLogger.info(logger, "dataSource: {}, sqlSessionFactory: {}", dataSource, sqlSessionFactoryObj);
        return sqlSessionFactoryObj;
    }

    @Bean(
//			name = "transactionManager"
    )
    public DataSourceTransactionManager transactionManager(DataSource dataSource) {
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager(dataSource);
        BLogger.info(logger, "dataSource: {}, transactionManager: {}", dataSource, transactionManager);
        return transactionManager;
    }
}
