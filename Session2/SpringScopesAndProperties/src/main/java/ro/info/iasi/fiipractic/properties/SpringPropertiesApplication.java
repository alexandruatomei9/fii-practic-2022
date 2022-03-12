package ro.info.iasi.fiipractic.properties;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.io.Resource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

public class SpringPropertiesApplication {

    public static void main(String[] args) throws Exception {
        ApplicationContext propertiesApplicationContext = new AnnotationConfigApplicationContext(PropertiesConfig.class);

        //System.out.println(propertiesApplicationContext.getBean("propertiesConfig"));

        //readEnvironmentProperty(propertiesApplicationContext);
        System.out.println("------------------");

        readResources(propertiesApplicationContext);
        System.out.println("------------------");
    }

    private static void readEnvironmentProperty(ApplicationContext applicationContext) {
        ConfigurableEnvironment environment = (ConfigurableEnvironment) applicationContext.getEnvironment();

        System.out.println("-- System properties --");

        environment.getSystemProperties().entrySet()
                .stream().limit(15)
                .forEach(e -> System.out.println(e.getKey() + " = " + e.getValue()));

        System.out.println("-------------");

        System.out.println("-- System Env properties --");

        environment.getSystemEnvironment().entrySet()
                .stream().limit(15)
                .forEach(e -> System.out.println(e.getKey() + " = " + e.getValue()));
        System.out.println("-------------");

        System.out.println(environment.getProperty("java.specification.version"));
    }


    public static void readResources(ApplicationContext applicationContext) throws IOException {
        Resource propertiesResource = applicationContext.getResource("test-resource.properties");

        Properties props = new Properties();
        props.load(propertiesResource.getInputStream());

        props.forEach((key, value) -> System.out.println(key + " = " + value));

        System.out.println("----------------");

        Resource textResource = applicationContext.getResource("file:///Users/aatomei/mule/SpringScopesAndProperties/src/main/resources/test-resource.txt");

        BufferedReader reader = new BufferedReader(new InputStreamReader(textResource.getInputStream()));

        while(reader.ready()) {
            System.out.println(reader.readLine());
        }
    }
}
