package ro.info.iasi.fiipractic.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan("ro.info.iasi.fiipractic.controller")
public class WebMvcConfig {

    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver vr = new InternalResourceViewResolver();

        // set location of views.
        vr.setPrefix("/");

        // set the extension of views.
        vr.setSuffix(".jsp");

        return vr;
    }
}
