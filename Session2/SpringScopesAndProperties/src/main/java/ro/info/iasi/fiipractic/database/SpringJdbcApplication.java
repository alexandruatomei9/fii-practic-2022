package ro.info.iasi.fiipractic.database;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringJdbcApplication {

    public static void main(String[] args) {
        ApplicationContext dbApplicationContext = new AnnotationConfigApplicationContext(DatabaseConfig.class);

        UserDAO userDao = (UserDAO) dbApplicationContext.getBean("userDAO");

        System.out.println(userDao.getCountOfUsers());
        System.out.println("------------");

        userDao.addUser("Alex", "Atomei", "alexandru.atomei@softvision.com", "pass");
        userDao.addUser("Emilian", "Marian", "emilian.marian@softvision.com", "pass");

        System.out.println(userDao.getCountOfUsers());
        System.out.println("------------");

        System.out.println(userDao.getAllUsers());
    }
}
