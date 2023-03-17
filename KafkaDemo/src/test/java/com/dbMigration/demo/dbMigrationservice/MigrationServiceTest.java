package com.dbMigration.demo.dbMigrationservice;

import com.dbMigration.demo.dbMigrationservice.MigrationService;
import com.dbMigration.demo.mysql.MysqlAccountRepo;
import com.dbMigration.demo.postgresql.PostgresqlAccountRepo;
import com.dbMigration.demo.payload.Account;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.BDDMockito.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class MigrationServiceTest {

    @InjectMocks
    private MigrationService migrationService;

    @Mock
    private MysqlAccountRepo mRepo;

    @Autowired
    private PostgresqlAccountRepo pRepo;

    private Account account;

    @BeforeEach
    public void setUpData(){
        account=Account.builder()
                .id(1)
                .customerName("Akash kadam")
                .isMigrate(false)
                .build();
    }

    @Test
    public void givenAccountObject_whenMigration_thanReturnNothing(){
    System.out.println("Account: "+account);
    System.out.println("MysqlRepo: "+mRepo);

        given(mRepo.getAllAccountsForMigration()).willReturn(List.of(account));

        willDoNothing().given(migrationService).migrationService();

        migrationService.migrationService();

        verify(mRepo,times(1)).save(any(Account.class));



    }


}
