package com.ioffice.main;




import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@EnableJpaRepositories("com.ioffice.repository")
@EntityScan("com.ioffice.model")
@SpringBootApplication(scanBasePackages = "com.ioffice.*")
public class IofficeCalenderApiApplication {
	static Logger logger=Logger.getLogger(IofficeCalenderApiApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(IofficeCalenderApiApplication.class, args);
		logger.setLevel(Level.INFO);
		logger.debug("Debug Message....");
		logger.info("Info Message....");
		logger.warn("Warn Message....");
		logger.error("Error Message....");
		logger.fatal("fatal Message....");
	}

}
