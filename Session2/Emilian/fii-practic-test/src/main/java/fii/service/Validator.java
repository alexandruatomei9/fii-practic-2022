package fii.service;

import fii.model.User;
import fii.service.interfaces.IValidator;

public class Validator implements IValidator {

  private final String bannedName;
  private final String bannedSurname;

  public Validator(String bannedName, String bannedSurname) {
    this.bannedName = bannedName;
    this.bannedSurname = bannedSurname;
  }

  public boolean isValid(User user) {
    if (user == null) {
      return false;
    }

    if (user.getName().isEmpty() || user.getSurname().isEmpty()) {
      return false;
    }

    if(user.getName().equals(bannedName) && user.getSurname().equals(bannedSurname)) {
      return false;
    }
    return true;
  }
}
