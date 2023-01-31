package com.ioffice.demo.service;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import com.ioffice.demo.model.Account;
import com.ioffice.demo.repo.mysql.MysqlAccountRepo;
import com.ioffice.demo.repo.postgresql.PostgresqlAccountRepo;

@Service
@EnableScheduling
public class DataBaseMIgrationService {

	@Autowired
	private MysqlAccountRepo mysqlRepo;
	@Autowired
	private PostgresqlAccountRepo postgresqlRepo;
	
	private static Logger logger=Logger.getLogger(DataBaseMIgrationService.class);
	
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
		
	List<Account> accounts=	mysqlRepo.getAllAccountsForMigration();

		accounts.stream()
				.forEach(account -> {
					System.out.println(account.getCustomerName()+" "
					          +account.getIsMigrate()+" "
								+account.getId());
					
					setFlag(account);
					
					postgresqlRepo.save(account);
					
					mysqlRepo.save(account);
					
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
