package ro.info.iasi.fiipractic.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan({ "ro.info.iasi.fiipractic.properties" })
@PropertySource("application.properties")
@PropertySource("${envTarget:dev}-application.properties")
public class PropertiesConfig {

    @Value( "${app.property1}" )
    private String firstAppProperty;

    @Value( "${app.property2}" )
    private int secondAppProperty;

    @Value( "${app.property3:default}" )
    private String thirdAppProperty;

    @Override
    public String toString() {
        return "PropertiesConfig{" +
                "firstAppProperty='" + firstAppProperty + '\'' +
                ", secondAppProperty=" + secondAppProperty +
                ", thirdAppProperty='" + thirdAppProperty + '\'' +
                '}';
    }
}
