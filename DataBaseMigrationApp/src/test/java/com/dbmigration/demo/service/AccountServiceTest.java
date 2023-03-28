package com.dbmigration.demo.service;

import com.dbmigration.demo.migrationservice.DataBaseMigrationService;
import com.dbmigration.demo.model.Account;
import com.dbmigration.demo.repo.postgresql.PostgresqlAccountRepo;
import com.dbmigration.demo.repo.mysql.MysqlAccountRepo;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.List;

@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {

    @Mock
    private MysqlAccountRepo mysqlAccountRepo;

    @Mock
    private PostgresqlAccountRepo postgresqlAccountRepo;

    @InjectMocks
    private AccountService accountService;

    Account account1;
    Account account2;

    @BeforeEach
    public void setUp(){
    account1=Account.builder()
            .id(1)
            .customerName("Akash Kadam")
            .isMigrate(false)
            .build();
        account2=Account.builder()
                .id(2)
                .customerName("Ajit Hirave")
                .isMigrate(false)
                .build();
    }


    @Test
    @DisplayName("test for get all accounts from mysql database ")
    public void givenListOfAccountObject_whenGetAllAccountsFromMysqlDataBase_thanReturnListOfAccountObjects(){
        BDDMockito.given(mysqlAccountRepo.findAll()).willReturn(List.of(account1,account2));
        List<Account> savedAccounts=accountService.getAllAccountsFromMysqlDataBase();
        Assertions.assertThat(savedAccounts.size()).isEqualTo(2);
    }
    @Test
    @DisplayName("test for get all accounts from postgresql database ")
    public void givenListOfAccountObject_whenGetAllAccountsFromPostgresqlDataBase_thanReturnListOfAccountsObjects(){
        BDDMockito.given(postgresqlAccountRepo.findAll()).willReturn(List.of(account1,account2));
        List<Account> savedAccounts=accountService.getAllAccountsFromPostgresqlDataBase();
        Assertions.assertThat(savedAccounts.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("test for get all account should be migrate")
    public void givenListOfObjects_whenGetAllEmployeeToBeMigrate_thanReturnListOfObjects(){
        BDDMockito.given(mysqlAccountRepo.getAllAccountsForMigration()).willReturn(List.of(account1,account2));
        List<Account> accountList=accountService.getAllAccountsToBeMigrate();
        Assertions.assertThat(accountList.size()).isEqualTo(2);
    }
    @Test
    @DisplayName("test for dump data in both database")
    public void givenAccountObject_whenDumpDataInBothDataBaseService_thanReturnNoting() throws Exception{
        BDDMockito.given(mysqlAccountRepo.save(account1)).willReturn(account1);
        BDDMockito.given(postgresqlAccountRepo.save(account1)).willReturn(account1);
        accountService.dupDataInBothDatabaseService(account1);
        BDDMockito.verify(mysqlAccountRepo, Mockito.times(1)).save(account1);
        BDDMockito.verify(postgresqlAccountRepo,Mockito.times(1)).save(account1);
    }
    @Test
    @DisplayName("test for create account")
    public void givenAccountObject_whenCreateAccount_thanReturnMessage(){
        boolean flag=false;
        String message="Account Created..";
        DataBaseMigrationService.setFlag(account1);
        BDDMockito.given(mysqlAccountRepo.save(account1)).willReturn(account1);
        BDDMockito.given(postgresqlAccountRepo.save(account1)).willReturn(account1);

        String actualMessage= accountService.createAccount(account1,flag);
        Assertions.assertThat(actualMessage).isEqualTo(message);
    }

}
