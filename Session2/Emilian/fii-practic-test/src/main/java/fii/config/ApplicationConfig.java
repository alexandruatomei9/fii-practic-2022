package fii.config;

import fii.service.Validator;
import fii.service.interfaces.IValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"fii.service"})
public class ApplicationConfig {

  @Bean(name = "validatorBean")
  public IValidator getValidator() {
    return new Validator("John",
        "Doe");
  }
}
