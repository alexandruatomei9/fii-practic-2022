package ro.info.iasi.fiipractic.actuator;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.info.Info.Builder;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;
import ro.info.iasi.fiipractic.model.User;
import ro.info.iasi.fiipractic.service.UserService;

@Component
public class CustomInfo implements InfoContributor {

  @Autowired
  private UserService userService;

  @Override
  public void contribute(Builder builder) {
    builder.withDetails(Map.of("userDetails", Map.of("name", "emilian",
    "surname", "Marian")));

    Map<String, Integer> usersByDomain = new HashMap<>();
    for(User user : userService.getAllUsers()) {
      String domain = user.getEmail().substring(user.getEmail().indexOf("@") + 1);
      if(usersByDomain.containsKey(domain)) {
        usersByDomain.put(domain, usersByDomain.get(domain) + 1);
      } else {
        usersByDomain.put(domain, 1);
      }
    }
    builder.withDetails(Map.of("usersByDomain", usersByDomain));
  }
}
