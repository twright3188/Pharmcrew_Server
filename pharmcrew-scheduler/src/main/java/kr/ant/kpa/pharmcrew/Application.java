package kr.ant.kpa.pharmcrew;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import com.bumdori.spring.BLogger;

import kr.ant.kpa.pharmcrew.config.RootConfig;

public class Application implements WebApplicationInitializer {
    private final Logger logger = LoggerFactory.getLogger(Application.class);

    public Application() {
        BLogger.info(logger, "constructor: {}", this);
    }

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
        rootContext.register(
                RootConfig.class
        );
        servletContext.addListener(new ContextLoaderListener(rootContext));
    }
}
