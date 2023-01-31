package com.example.demo.service;

import com.example.demo.dbMRepo.MysqlAccountRepo;
import com.example.demo.dbPRepo.PostgresqlAccountRepo;
import com.example.demo.payload.Account;

import org.assertj.core.api.Assertions;
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
