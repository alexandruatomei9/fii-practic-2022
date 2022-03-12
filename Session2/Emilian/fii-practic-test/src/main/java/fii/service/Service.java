package fii.service;

import fii.model.User;
import fii.service.interfaces.IValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope
@org.springframework.stereotype.Service
public class Service {

  private final IValidator validator;

  @Autowired
  public Service(IValidator validator) {
    this.validator = validator;
  }

  public void save(User user) {
    if (validator.isValid(user)) {
      System.out.println("User " + user.getName()
          + " " + user.getSurname() + " saved successfully.");
    } else {
      System.out.println("User you are trying to save contains errors.");
    }
  }

}
