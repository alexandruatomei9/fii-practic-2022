package ro.info.iasi.fiipractic.database;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
@ComponentScan({"ro.info.iasi.fiipractic.database"})
@PropertySource("db-application.properties")
public class DatabaseConfig {

    @Value( "${db.host}" )
    private String dbHost;

    @Value( "${db.port}" )
    private int dbPort;

    @Value( "${db.database}" )
    private String database;

    @Value( "${db.username}" )
    private String dbUsername;

    @Value( "${db.password}" )
    private String dbPassword;

    @Bean
    public DataSource mysqlDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl(String.format("jdbc:mysql://%s:%s/%s", dbHost, dbPort, database));
        dataSource.setUsername(dbUsername);
        dataSource.setPassword(dbPassword);

        return dataSource;
    }
}
