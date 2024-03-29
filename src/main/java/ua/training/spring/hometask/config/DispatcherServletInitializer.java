package ua.training.spring.hometask.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import org.springframework.ws.transport.http.MessageDispatcherServlet;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.ServletRegistration.Dynamic;
import java.io.File;

@Configuration
public class DispatcherServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    private static final int MAX_UPLOAD_SIZE_IN_MB = 1000;

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{WebMvcConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return null;
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        super.onStartup(servletContext);
        servletContext.setInitParameter("spring.profiles.active", "IN_MEMORY");

        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
        ctx.register(WebMvcConfig.class);
        ctx.setServletContext(servletContext);

        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(ctx);
        servlet.setTransformWsdlLocations(true);
        Dynamic dynamic = servletContext.addServlet("soapServlet", servlet);
        dynamic.addMapping("/ws/*");
        dynamic.setLoadOnStartup(1);

    }

    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
        File uploadDirectory = new File(System.getProperty("java.io.tmpdir"));

        MultipartConfigElement multipartConfigElement =
                new MultipartConfigElement(uploadDirectory.getAbsolutePath(),
                        MAX_UPLOAD_SIZE_IN_MB, MAX_UPLOAD_SIZE_IN_MB * 2, MAX_UPLOAD_SIZE_IN_MB / 2);

        registration.setMultipartConfig(multipartConfigElement);
    }
}
