package com.ioffice.demo.migrationservice;
import java.util.List;

import com.ioffice.demo.service.AccountService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import com.ioffice.demo.model.Account;

@Service
@EnableScheduling
public class DataBaseMigrationService {

	@Autowired
	private AccountService accountService;
	
	private static Logger logger=Logger.getLogger(DataBaseMigrationService.class);
	
	@Value("${isMigration.complete}")
	private Boolean canStop;
	
	
	/*
	 * Migration service which execute after fix delay.
	 * 
	 * @param 
	 * @return 
	 * 
	 * */
	@Scheduled(cron="5 * * ? * *")
	public void migrationService() {
		logger.info("Migration Service is executed.... after each 5 Sec.");
		logger.info("value from application.properties file :-"+canStop);
	List<Account> accounts=	accountService.getAllAccountsToBeMigrate();
		accounts.stream()
				.forEach(account -> {
					System.out.println(account.getCustomerName()+" "
					          +account.getIsMigrate()+" "
								+account.getId());
					setFlag(account);
					accountService.dupDataInBothDatabaseService(account);
				});
	}
	
	
	
	/*
	 * Set migration flag to true after migration.
	 * 
	 * @param Account
	 * @return Account
	 * 
	 * */
	public static void setFlag(Account account) {
		account.setIsMigrate(true);
	}
	
	
	
}
