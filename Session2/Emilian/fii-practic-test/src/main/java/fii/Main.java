package fii;

import fii.config.ApplicationConfig;
import fii.model.User;
import fii.service.Service;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

  public static void main(String[] args) {
    ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ApplicationConfig.class);

    Service service = applicationContext.getBean(Service.class);
    User myUser = new User("Emilian", "Marian");

    service.save(myUser);
  }
}
