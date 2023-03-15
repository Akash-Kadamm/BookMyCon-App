package com.dbmigration.demo.migrationservice;

import com.dbmigration.demo.model.Address;
import com.dbmigration.demo.model.Company;
import com.dbmigration.demo.model.Department;
import com.dbmigration.demo.model.User;
import com.dbmigration.demo.service.AddressService;
import com.dbmigration.demo.service.CompanyService;
import com.dbmigration.demo.service.DepartmentService;
import com.dbmigration.demo.service.UserService;
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
public class UserMigrationServiceTest {
    @Mock
    private UserService userService;
    @Mock
    private CompanyService companyService;
    @Mock
    private DepartmentService departmentService;
    @Mock
    private AddressService addressService;
    @InjectMocks
    private UserMigrationService userMigrationService;
    User user;
    Department department;
    Company company;
    Address address;

    @BeforeEach
    public void setUp(){
        user=User.builder()
                .userId(1)
                .userEmail("akashkad@cybage.com")
                .userName("Akash Kadam")
                .userPassword("ak@123")
                .userContactNumber("7038967693")
                .addressId(1)
                .companyId(1)
                .isMigrate(false)
                .build();
        department=Department.builder()
                .departmentId(1)
                .departmentName("QA")
                .build();
        company=Company.builder()
                .companyId(1)
                .companyName("Cybage")
                .companyAddress("Pune")
                .build();
        address=Address.builder()
                .addressId(1)
                .localLandmark("Akurdi")
                .homeNumber("302")
                .cityName("pune")
                .pinCode("431601")
                .build();
    }

    @Test
    @DisplayName("Test for User migration by userId migration done with saving other entity.")
    public void givenUserId_whenUserMigrationServiceByUserId_thanReturnMessage_firstCase(){
        BDDMockito.given(userService.getUserByUserId(BDDMockito.anyInt())).willReturn(user);
        BDDMockito.given(companyService.getByCompanyId(BDDMockito.anyInt())).willReturn(company);
        BDDMockito.given(addressService.getAddressById(BDDMockito.anyInt())).willReturn(address);
        BDDMockito.given(departmentService.getDepartmentById(BDDMockito.anyInt())).willReturn(department);
        BDDMockito.given(companyService.getCompanyFromPostgresql(BDDMockito.anyInt())).willReturn(null);
        BDDMockito.given(addressService.getAddressFromPostgresql(BDDMockito.anyInt())).willReturn(null);
        BDDMockito.given(departmentService.getDepartmentFromPostgresql(BDDMockito.anyInt())).willReturn(null);
        BDDMockito.given(userService.saveUserInMysql(BDDMockito.any(User.class))).willReturn(user);
        BDDMockito.given(userService.saveUserInPostgresql(BDDMockito.any(User.class))).willReturn(user);
        BDDMockito.given(companyService.saveCompany(BDDMockito.any(Company.class))).willReturn(company);
        BDDMockito.given(addressService.saveAddress(BDDMockito.any(Address.class))).willReturn(ResponseMessage.ADDRESS_RECORD_SAVED);
        BDDMockito.given(departmentService.saveDepartment(BDDMockito.any(Department.class))).willReturn(ResponseMessage.DEPARTMENT_RECORD_SAVED);

        String message=userMigrationService.userMigrationServiceByUserId(user.getUserId());

        Assertions.assertThat(message).isEqualTo("Migrated user : " + user.getUserName());
    }
    @Test
    @DisplayName("Test for User migration by userId user is already migrated..")
    public void givenUserId_whenUserMigrationServiceByUserId_thanReturnMessage_secondCase(){
        BDDMockito.given(userService.getUserByUserId(BDDMockito.anyInt())).willReturn(user);
        UserService.setFlag(user);

        String message=userMigrationService.userMigrationServiceByUserId(user.getUserId());

        Assertions.assertThat(message).isEqualTo("User is already migrated..");
    }

    @Test
    @DisplayName("Test for User migration by userId migration done without saving other entity.")
    public void givenUserId_whenUserMigrationServiceByUserId_thanReturnMessage_thirdCase(){
        BDDMockito.given(userService.getUserByUserId(BDDMockito.anyInt())).willReturn(user);
        BDDMockito.given(companyService.getByCompanyId(BDDMockito.anyInt())).willReturn(company);
        BDDMockito.given(addressService.getAddressById(BDDMockito.anyInt())).willReturn(address);
        BDDMockito.given(departmentService.getDepartmentById(BDDMockito.anyInt())).willReturn(department);
        BDDMockito.given(companyService.getCompanyFromPostgresql(BDDMockito.anyInt())).willReturn(company);
        BDDMockito.given(addressService.getAddressFromPostgresql(BDDMockito.anyInt())).willReturn(address);
        BDDMockito.given(departmentService.getDepartmentFromPostgresql(BDDMockito.anyInt())).willReturn(department);
        BDDMockito.given(userService.saveUserInMysql(BDDMockito.any(User.class))).willReturn(user);
        BDDMockito.given(userService.saveUserInPostgresql(BDDMockito.any(User.class))).willReturn(user);

        String message=userMigrationService.userMigrationServiceByUserId(user.getUserId());

        Assertions.assertThat(message).isEqualTo("Migrated user : " + user.getUserName());
    }
    @Test
    @DisplayName("Test for Migration service by company name All users already migrated")
    public void givenCompanyName_whenMigrationServiceByCompanyName_thanReturnMessage_firstCase(){
        BDDMockito.given(companyService.getCompanyByCompanyName(BDDMockito.anyString())).willReturn(company);
        BDDMockito.given(userService.getAllUsersByCompanyId(BDDMockito.anyInt())).willReturn(List.of());

        String message=userMigrationService.migrationServiceByCompanyName(company.getCompanyName());

        Assertions.assertThat(message).isEqualTo("Already All users migrated of this company....:"+company.getCompanyName());
    }

    @Test
    @DisplayName("Test for Migration service by company name with saving other entity")
    public void givenCompanyName_whenMigrationServiceByCompanyName_thanReturnMessage_secondCase(){
        BDDMockito.given(companyService.getCompanyByCompanyName(BDDMockito.anyString())).willReturn(company);
        BDDMockito.given(userService.getAllUsersByCompanyId(BDDMockito.anyInt())).willReturn(List.of(user));
        BDDMockito.given(companyService.getCompanyFromPostgresql(BDDMockito.anyInt())).willReturn(null);
        BDDMockito.given(addressService.getAddressFromPostgresql(BDDMockito.anyInt())).willReturn(null);
        BDDMockito.given(departmentService.getDepartmentFromPostgresql(BDDMockito.anyInt())).willReturn(null);

        BDDMockito.given(addressService.getAddressById(BDDMockito.anyInt())).willReturn(address);
        BDDMockito.given(departmentService.getDepartmentById(BDDMockito.anyInt())).willReturn(department);

        BDDMockito.given(userService.saveUserInMysql(BDDMockito.any(User.class))).willReturn(user);
        BDDMockito.given(userService.saveUserInPostgresql(BDDMockito.any(User.class))).willReturn(user);

        BDDMockito.given(companyService.saveCompany(BDDMockito.any(Company.class))).willReturn(company);
        BDDMockito.given(addressService.saveAddress(BDDMockito.any(Address.class))).willReturn(ResponseMessage.ADDRESS_RECORD_SAVED);
        BDDMockito.given(departmentService.saveDepartment(BDDMockito.any(Department.class))).willReturn(ResponseMessage.DEPARTMENT_RECORD_SAVED);

        String message=userMigrationService.migrationServiceByCompanyName(company.getCompanyName());

        Assertions.assertThat(message).isEqualTo("All users migrated of this company ....:"+company.getCompanyName());
    }

    @Test
    @DisplayName("Test for Migration service by company name without saving other entity")
    public void givenCompanyName_whenMigrationServiceByCompanyName_thanReturnMessage_thirdCase(){
        BDDMockito.given(companyService.getCompanyByCompanyName(BDDMockito.anyString())).willReturn(company);
        BDDMockito.given(userService.getAllUsersByCompanyId(BDDMockito.anyInt())).willReturn(List.of(user));
        BDDMockito.given(companyService.getCompanyFromPostgresql(BDDMockito.anyInt())).willReturn(company);
        BDDMockito.given(addressService.getAddressFromPostgresql(BDDMockito.anyInt())).willReturn(address);
        BDDMockito.given(departmentService.getDepartmentFromPostgresql(BDDMockito.anyInt())).willReturn(department);

        BDDMockito.given(addressService.getAddressById(BDDMockito.anyInt())).willReturn(address);
        BDDMockito.given(departmentService.getDepartmentById(BDDMockito.anyInt())).willReturn(department);

        BDDMockito.given(userService.saveUserInMysql(BDDMockito.any(User.class))).willReturn(user);
        BDDMockito.given(userService.saveUserInPostgresql(BDDMockito.any(User.class))).willReturn(user);

        String message=userMigrationService.migrationServiceByCompanyName(company.getCompanyName());

        Assertions.assertThat(message).isEqualTo("All users migrated of this company ....:"+company.getCompanyName());
    }




}
