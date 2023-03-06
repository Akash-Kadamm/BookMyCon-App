package com.dbmigration.demo.service;

import com.dbmigration.demo.model.Company;
import com.dbmigration.demo.repo.mysql.MysqlCompanyRepo;
import com.dbmigration.demo.repo.postgresql.PostgresqlCompanyRepo;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class CompanyServiceTest {

    @Mock
    private MysqlCompanyRepo mysqlCompanyRepo;
    @Mock
    private PostgresqlCompanyRepo postgresqlCompanyRepo;

    @InjectMocks
    private CompanyService companyService;

    Company company;

    @BeforeEach
    public void setUp(){
        company=Company.builder()
                .companyId(1)
                .companyName("Cybage Software Pvt. Ltd. Pune")
                .companyAddress("kalyani nagar pune")
                .build();
    }

    @Test
    @DisplayName("Test for Fetch All Companies.")
    public void given_whenGetAllCompany_thanListOfCompany(){
        BDDMockito.given(mysqlCompanyRepo.findAll()).willReturn(List.of(company));
        List<Company> companies=companyService.getAllCompany();
        Assertions.assertThat(companies.size()).isEqualTo(1);
    }

    @Test
    @DisplayName("Test for save company in database.")
    public void givenCompanyObject_whenSaveCompany_thanMessage(){
        BDDMockito.given(postgresqlCompanyRepo.save(BDDMockito.any(Company.class))).willReturn(company);
        Company savedCompany=companyService.saveCompany(company);
        Assertions.assertThat(savedCompany.getCompanyId()).isEqualTo(1);
        Assertions.assertThat(savedCompany.getCompanyName()).isEqualTo("Cybage Software Pvt. Ltd. Pune");
        Assertions.assertThat(savedCompany.getCompanyAddress()).isEqualTo("kalyani nagar pune");
    }

    @Test
    @DisplayName("Test for Delete Company.")
    public void givenCompanyId_whenDeleteCompany_thanMessage(){
        BDDMockito.willDoNothing().given(mysqlCompanyRepo).deleteById(BDDMockito.anyInt());
        String message= companyService.deleteCompany(1);
        Assertions.assertThat(message).isEqualTo("Company is deleted from record....");
    }

    @Test
    @DisplayName("Test for Get all users to be migrate.")
    public void givenListOfCompany_whenGetAllUsers_thanListOfCompany(){
        String companyName="Company is deleted from record....";
        BDDMockito.given(mysqlCompanyRepo.fetchUsers(BDDMockito.anyString())).willReturn(List.of(company));
        List<Company> listOfUsers=companyService.getAllUsersToBeMigrate(companyName);
        Assertions.assertThat(listOfUsers.size()).isEqualTo(1);
    }

}
