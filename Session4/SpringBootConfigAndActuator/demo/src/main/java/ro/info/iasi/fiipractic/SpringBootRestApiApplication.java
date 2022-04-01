package ro.info.iasi.fiipractic;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootRestApiApplication {

	@Value("${my.variable:value}")
	private String myVariable;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootRestApiApplication.class, args);
	}

	@PostConstruct
	public void doSomething() {
		System.out.println(" -  - - -- - - --  - -- - ");
		System.out.println(myVariable);
		System.out.println(" -  - - -- - - --  - -- - ");
	}
}
