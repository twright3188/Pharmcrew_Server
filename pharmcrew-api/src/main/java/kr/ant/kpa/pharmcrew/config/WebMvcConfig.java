package kr.ant.kpa.pharmcrew.config;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.BeanNameViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.bumdori.spring.BLogger;
import com.bumdori.spring.interceptor.LoggingInterceptor;

import kr.ant.kpa.pharmcrew.interceptor.SessionInterceptor;

@Configuration
@EnableWebMvc
@ComponentScan(
        basePackages = {
                "kr.ant.kpa.pharmcrew",
        },
        useDefaultFilters = false,
        includeFilters = {
                @ComponentScan.Filter(type = FilterType.ANNOTATION, value = {
                        Controller.class,
                })
        }
)
@EnableAspectJAutoProxy
public class WebMvcConfig implements WebMvcConfigurer {
    private final Logger logger = LoggerFactory.getLogger(WebMvcConfig.class);

    public WebMvcConfig() {
        BLogger.info(logger, "constructor: {}", this);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
        registry.addResourceHandler("/apitest/**").addResourceLocations("/apitest/");
    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        BeanNameViewResolver beanNameViewResolver = new BeanNameViewResolver();
        beanNameViewResolver.setOrder(1);
        registry.viewResolver(beanNameViewResolver);

        InternalResourceViewResolver internalResourceViewResolver = new InternalResourceViewResolver("/WEB-INF/views/", ".jsp");
        internalResourceViewResolver.setViewClass(JstlView.class);
        internalResourceViewResolver.setOrder(2);
        registry.viewResolver(internalResourceViewResolver);
    }

    // <mvc:default-servlet-handler />
//    @Override
//    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
//        configurer.enable();
//    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        SessionInterceptor sessionInterceptor = new SessionInterceptor();
        registry.addInterceptor(sessionInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(
                        "/resources/**",
                        "/apitest/**"
                );
        LoggingInterceptor loggingInterceptor = new LoggingInterceptor();
        registry.addInterceptor(loggingInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(
                        "/resources/**",
                        "/apitest/**"
                );
    }
}
