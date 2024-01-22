package kr.ant.kpa.pharmcrew.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.bumdori.spring.BLogger;

@Configuration
public class MultipartConfig {
	
	private static final Logger logger = LoggerFactory.getLogger(MultipartConfig.class);

	public MultipartConfig() {
		super();
		BLogger.debug(logger, "constructor: {}", this);
	}

	@Bean
	public MultipartResolver multipartResolver() {
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
		multipartResolver.setMaxUploadSize(9999999999L);
		multipartResolver.setMaxInMemorySize(1048570);
		return multipartResolver;
	}
}
