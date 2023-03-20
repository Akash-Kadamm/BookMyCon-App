package com.dbmigration.demo.service;

import com.dbmigration.demo.model.Company;
import com.dbmigration.demo.repo.mysql.MysqlCompanyRepo;
import com.dbmigration.demo.repo.postgresql.PostgresqlCompanyRepo;
import com.dbmigration.demo.utility.ResponseMessage;
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
import java.util.Optional;

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
        ResponseMessage message= companyService.deleteCompany(1);
        Assertions.assertThat(message).isEqualTo(ResponseMessage.COMPANY_RECORD_DELETED);
    }

    @Test
    @DisplayName("Test for Get Company By Company Name.")
    public void givenCompanyName_whenGetCompanyByCompanyName_thanReturnCompany(){
        BDDMockito.given(mysqlCompanyRepo.fetchCompanyDetails(BDDMockito.anyString())).willReturn(company);
        Company savedCompany=companyService.getCompanyByCompanyName(company.getCompanyName());
        Assertions.assertThat(savedCompany.getCompanyId()).isEqualTo(1);
        Assertions.assertThat(savedCompany.getCompanyName()).isEqualTo("Cybage Software Pvt. Ltd. Pune");
        Assertions.assertThat(savedCompany.getCompanyAddress()).isEqualTo("kalyani nagar pune");
    }
    @Test
    @DisplayName("Test for Get Company By CompanyId. ")
    public void givenCompanyId_whenGetByCompanyId_thanReturnCompany(){
        BDDMockito.given(mysqlCompanyRepo.findById(BDDMockito.anyInt())).willReturn(Optional.of(company));
        Company savedCompany=companyService.getByCompanyId(company.getCompanyId());
        Assertions.assertThat(savedCompany.getCompanyId()).isEqualTo(1);
        Assertions.assertThat(savedCompany.getCompanyName()).isEqualTo("Cybage Software Pvt. Ltd. Pune");
        Assertions.assertThat(savedCompany.getCompanyAddress()).isEqualTo("kalyani nagar pune");
    }
    @Test
    @DisplayName("Test for Get Company from postgresql Database Positive scenario.")
    public void givenCompanyId_whenGetCompanyFromPostgresql_thanReturnCompany(){
        BDDMockito.given(postgresqlCompanyRepo.findById(BDDMockito.anyInt())).willReturn(Optional.of(company));
        Company savedCompany=companyService.getCompanyFromPostgresql(company.getCompanyId());
        Assertions.assertThat(savedCompany.getCompanyId()).isEqualTo(1);
        Assertions.assertThat(savedCompany.getCompanyName()).isEqualTo("Cybage Software Pvt. Ltd. Pune");
        Assertions.assertThat(savedCompany.getCompanyAddress()).isEqualTo("kalyani nagar pune");
    }

    @Test
    @DisplayName("Test for Get Company from postgresql Database negative scenario.")
    public void givenCompanyId_whenGetCompanyFromPostgresql_thanReturnNull(){
        BDDMockito.given(postgresqlCompanyRepo.findById(BDDMockito.anyInt())).willReturn(Optional.empty());
        Company savedCompany=companyService.getCompanyFromPostgresql(company.getCompanyId());
        Assertions.assertThat(savedCompany).isEqualTo(null);
    }
}
