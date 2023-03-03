package com.dbmigration.demo.migrationservice;

import com.dbmigration.demo.model.Account;
import com.dbmigration.demo.repo.postgresql.PostgresqlAccountRepo;
import com.dbmigration.demo.service.AccountService;
import com.dbmigration.demo.repo.mysql.MysqlAccountRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class DataMigrationServiceTest {

    @Mock
    private MysqlAccountRepo mysqlAccountRepo;

    @Mock
    private PostgresqlAccountRepo postgresqlAccountRepo;

    @Mock
    private AccountService accountService;

    @InjectMocks
    private DataBaseMigrationService dataBaseMigrationService;

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
    @DisplayName("test for migrating data by @Scheduled ")
    public void givenListOfAccountObjects_whenMigrationService_thanReturnNothing(){
        BDDMockito.given(accountService.getAllAccountsToBeMigrate()).willReturn(List.of(account1,account2));
        BDDMockito.willDoNothing().given(accountService).dupDataInBothDatabaseService(account1);

        dataBaseMigrationService.migrationService();

        BDDMockito.verify(accountService, Mockito.times(1)).getAllAccountsToBeMigrate();
        BDDMockito.verify(accountService,Mockito.times(1)).dupDataInBothDatabaseService(account1);
    }


}
