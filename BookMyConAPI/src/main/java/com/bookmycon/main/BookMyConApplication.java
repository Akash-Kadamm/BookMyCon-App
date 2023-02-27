package com.bookmycon.main;




import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@EnableJpaRepositories("com.bookmycon.repository")
@EntityScan("com.bookmycon.model")
@SpringBootApplication(scanBasePackages = "com.bookmycon.*")
public class BookMyConApplication {
	static Logger logger=Logger.getLogger(BookMyConApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(BookMyConApplication.class, args);
		logger.setLevel(Level.INFO);
		logger.debug("Debug Message....");
		logger.info("Info Message....");
		logger.warn("Warn Message....");
		logger.error("Error Message....");
		logger.fatal("fatal Message....");
	}

}
