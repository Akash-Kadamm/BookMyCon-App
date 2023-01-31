package com.example.demo.dbMRepo;

import ch.qos.logback.core.CoreConstants;
import ch.qos.logback.core.net.SyslogOutputStream;
import com.example.demo.payload.Account;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.BDDAssertions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;


@DataJpaTest
public class MysqlAccountRepoTest {

  @Autowired
    private MysqlAccountRepo accountRepo;

    private Account account;

    @BeforeEach
    public void setUpData(){
        account=Account.builder()
                .id(1)
                .customerName("Akash Kadam")
                .isMigrate(false)
                .build();

    }

    @Test
    @DisplayName("Test  for get all Accounts which are not migrated method")
     public void givenNothing_whenGetAllAccountForMigration_thanReturnListOFAccounts(){
        accountRepo.save(account);
        System.out.println("Account repo :"+accountRepo);
        System.out.println("Account  :"+account);

        //BDDMockito.given(accountRepo.getAllAccountsForMigration()).willReturn(List.of(account));

        List<Account> needMigrationAccounts=accountRepo.getAllAccountsForMigration();

        Assertions.assertThat(needMigrationAccounts.size()).isEqualTo(1);

     }






}
