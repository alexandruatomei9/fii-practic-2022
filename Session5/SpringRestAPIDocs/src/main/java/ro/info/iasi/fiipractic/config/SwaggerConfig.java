package ro.info.iasi.fiipractic.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
@EnableWebMvc
@EnableOpenApi
@ComponentScan("ro.info.iasi.fiipractic")
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.OAS_30)
                .select()
                .apis(RequestHandlerSelectors.basePackage("ro.info.iasi.fiipractic.controller"))
//                .paths(PathSelectors.any())       // display all endpoints
                .paths(PathSelectors.ant("/spring-rest-api-docs-1.0.0-SNAPSHOT/user*"))
                .build()
                .apiInfo(metadata());
    }

    private ApiInfo metadata() {
        return new ApiInfoBuilder()
                .title("TWITTER REST API")
                .description("Some custom description of API.")
                .version("API TOS")
                .termsOfServiceUrl("Terms of service")
                .contact(new Contact("John Doe", "www.softvision.com", "me@softvision.com"))
                .license("License of API")
                .licenseUrl("API license URL")
                .build();
    }

}
