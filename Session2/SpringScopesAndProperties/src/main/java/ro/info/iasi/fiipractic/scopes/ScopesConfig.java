package ro.info.iasi.fiipractic.scopes;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
@ComponentScan({ "ro.info.iasi.fiipractic.scopes" })
public class ScopesConfig {

    @Bean
    @Scope("singleton")
    public User userSingleton() {
        return new User();
    }

    @Bean
    @Scope("prototype")
    public User userPrototype() {
        return new User();
    }
}
