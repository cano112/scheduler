package pl.edu.agh.wiet.studiesplanner;

import org.h2.server.web.WebServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.*;

@Configuration
@ComponentScan("pl.edu.agh.wiet.studiesplanner")
public class AppConfig  {

    @Bean
    public ServletRegistrationBean<WebServlet> h2servletRegistration() {
        ServletRegistrationBean<WebServlet> registration = new ServletRegistrationBean<>(new WebServlet());
        registration.addUrlMappings("/h2/*");
        return registration;
    }
}
