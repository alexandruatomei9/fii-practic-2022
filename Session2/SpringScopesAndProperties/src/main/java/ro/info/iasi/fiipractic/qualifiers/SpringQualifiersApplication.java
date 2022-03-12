package ro.info.iasi.fiipractic.qualifiers;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringQualifiersApplication {

    public static void main(String[] args) {
        ApplicationContext qualifiersApplicationContext = new AnnotationConfigApplicationContext(QualifiersConfig.class);

        FooService service = qualifiersApplicationContext.getBean(FooService.class);

        service.format();
    }
}
