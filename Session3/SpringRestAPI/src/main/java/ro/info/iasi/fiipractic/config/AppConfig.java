package ro.info.iasi.fiipractic.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.sql.DataSource;

@Configuration
@EnableWebMvc
@ComponentScan("ro.info.iasi.fiipractic")
@PropertySource("classpath:db-application.properties")
public class AppConfig {

    @Value( "${db.url}" )
    private String url;

    @Value( "${db.username}" )
    private String dbUsername;

    @Value( "${db.password}" )
    private String dbPassword;

    @Value( "${db.driverClassName}" )
    private String dbDriverClassName;

    @Bean
    public DataSource mysqlDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(dbDriverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(dbUsername);
        dataSource.setPassword(dbPassword);

        return dataSource;
    }
}
