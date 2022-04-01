package ro.info.iasi.fiipractic.scopes;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringScopesApplication {

    public static void main(String[] args) {
        ApplicationContext scopesApplicationContext = new AnnotationConfigApplicationContext(ScopesConfig.class);

        singletonExample(scopesApplicationContext);
        System.out.println("------------------");

        prototypeExample(scopesApplicationContext);
        System.out.println("------------------");
    }

    private static void singletonExample(ApplicationContext applicationContext) {
        User userSingletonA = (User) applicationContext.getBean("userSingleton");
        User userSingletonB = (User) applicationContext.getBean("userSingleton");

        System.out.println(userSingletonB.getName());

        userSingletonA.setName("FiiPractic 2022 Participant");

        System.out.println(userSingletonB.getName());
        System.out.println(userSingletonA.getName());
    }

    private static void prototypeExample(ApplicationContext applicationContext) {
        User userPrototypeA = (User) applicationContext.getBean("userPrototype");
        User userPrototypeB = (User) applicationContext.getBean("userPrototype");

        System.out.println(userPrototypeA.getName());
        System.out.println(userPrototypeB.getName());

        userPrototypeA.setName("FiiPractic 2022 Participant 1");
        userPrototypeB.setName("FiiPractic 2022 Participant 2");

        System.out.println(userPrototypeA.getName());
        System.out.println(userPrototypeB.getName());
    }
}
