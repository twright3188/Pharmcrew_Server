package kr.ant.kpa.pharmcrew;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.session.web.context.AbstractHttpSessionApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.DispatcherServlet;

import com.bumdori.spring.BLogger;
import com.bumdori.spring.filter.HttpWrappingFilter;

import kr.ant.kpa.pharmcrew.config.RedisSessionConfig;
import kr.ant.kpa.pharmcrew.config.RootConfig;
import kr.ant.kpa.pharmcrew.config.WebMvcConfig;

public class Application extends AbstractHttpSessionApplicationInitializer {
    private final Logger logger = LoggerFactory.getLogger(Application.class);

    public Application() {
        super(
                RootConfig.class,
                RedisSessionConfig.class
        );
        BLogger.info(logger, "constructor: {}", this);
    }

    @Override
    protected void beforeSessionRepositoryFilter(ServletContext servletContext) {
        FilterRegistration encodingFilter = servletContext.addFilter("encodingFilter", CharacterEncodingFilter.class);
        encodingFilter.setInitParameter("encoding", "UTF-8");
        encodingFilter.addMappingForServletNames(null, false, "dispatcher");

        FilterRegistration httpMethodFilter = servletContext.addFilter("httpMethodFilter", HiddenHttpMethodFilter.class);
        httpMethodFilter.addMappingForServletNames(null, false, "dispatcher");

        FilterRegistration httpWrappingFilter = servletContext.addFilter("httpWrappingFilter", HttpWrappingFilter.class);
        httpWrappingFilter.addMappingForServletNames(null, false, "dispatcher");

        AnnotationConfigWebApplicationContext dispatcherContext = new AnnotationConfigWebApplicationContext();
        dispatcherContext.register(
                WebMvcConfig.class
        );
        DispatcherServlet servlet = new DispatcherServlet(dispatcherContext);
        ServletRegistration.Dynamic dispatcher = servletContext.addServlet("dispatcher", servlet);
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");
    }

    @Override
    public void onStartup(ServletContext servletContext) {
//        FilterRegistration encodingFilter = servletContext.addFilter("encodingFilter", CharacterEncodingFilter.class);
//        encodingFilter.setInitParameter("encoding", "UTF-8");
//        encodingFilter.addMappingForServletNames(null, false, "dispatcher");
//
//        FilterRegistration httpMethodFilter = servletContext.addFilter("httpMethodFilter", HiddenHttpMethodFilter.class);
//        httpMethodFilter.addMappingForServletNames(null, false, "dispatcher");
//
//        FilterRegistration httpWrappingFilter = servletContext.addFilter("httpWrappingFilter", HttpWrappingFilter.class);
//        httpWrappingFilter.addMappingForServletNames(null, false, "dispatcher");
//
//        AnnotationConfigWebApplicationContext dispatcherContext = new AnnotationConfigWebApplicationContext();
//        dispatcherContext.register(
//                WebMvcConfig.class
//        );
//        DispatcherServlet servlet = new DispatcherServlet(dispatcherContext);
//        ServletRegistration.Dynamic dispatcher = servletContext.addServlet("dispatcher", servlet);
//        dispatcher.setLoadOnStartup(1);
//        dispatcher.addMapping("/");

        super.onStartup(servletContext);
    }
}
