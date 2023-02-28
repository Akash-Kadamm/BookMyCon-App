package com.ioffice.demo.service;

import com.ioffice.demo.migrationservice.DataBaseMigrationService;
import com.ioffice.demo.model.Account;
import com.ioffice.demo.repo.mysql.MysqlAccountRepo;
import com.ioffice.demo.repo.postgresql.PostgresqlAccountRepo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    @Autowired
    private MysqlAccountRepo mysqlAccountRepo;
    @Autowired
    private PostgresqlAccountRepo postgresqlAccountRepo;

    private static Logger logger= Logger.getLogger(AccountService.class);




    public List<Account>getAllAccountsFromMysqlDataBase(){
        return mysqlAccountRepo.findAll();
    }

    public List<Account> getAllAccountsFromPostgresqlDataBase(){
        return postgresqlAccountRepo.findAll();
    }

    public List<Account> getAllAccountsToBeMigrate(){
        return mysqlAccountRepo.getAllAccountsForMigration();
    }

    public void  dupDataInBothDatabaseService(Account account){
        mysqlAccountRepo.save(account);
        postgresqlAccountRepo.save(account);
    }

    public String createAccount(Account account,boolean canStop){
        logger.info(" Create new account..."+account);
        logger.info("setting IsMigrated true");
        DataBaseMigrationService.setFlag(account);
        // check until  data is completely migrate or not.
        // if yes than no need to dump the data in mysql database.
        if(!canStop) {
            mysqlAccountRepo.save(account);
            logger.info("Save in mysql database "+account);
        }
        postgresqlAccountRepo.save(account);
        logger.info("Save in postgresql database "+account);
        return "Account Created..";
    }
}
