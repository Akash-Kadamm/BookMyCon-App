package com.dbMigration.demo.dbMigrationservice;

import com.dbMigration.demo.kafka.producer.AddressProducer;
import com.dbMigration.demo.kafka.producer.CompanyProducer;
import com.dbMigration.demo.kafka.producer.DepartmentProducer;
import com.dbMigration.demo.kafka.producer.UserProducer;
import com.dbMigration.demo.payload.Address;
import com.dbMigration.demo.payload.Company;
import com.dbMigration.demo.payload.Department;
import com.dbMigration.demo.payload.User;
import com.dbMigration.demo.service.AddressService;
import com.dbMigration.demo.service.CompanyService;
import com.dbMigration.demo.service.DepartmentService;
import com.dbMigration.demo.service.UserService;
import com.dbMigration.demo.utility.ResponseMessage;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

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
    @Mock
    private UserProducer userProducer;
    @Mock
    private AddressProducer addressProducer;
    @Mock
    private DepartmentProducer departmentProducer;
    @Mock
    private CompanyProducer companyProducer;
    User user;
    Department department;
    Company company;
    Address address;

    @BeforeEach
    public void setUp() {
        user = User.builder()
                .userId(1)
                .userEmail("akashkad@cybage.com")
                .userName("Akash Kadam")
                .userPassword("ak@123")
                .userContactNumber("7038967693")
                .addressId(1)
                .companyId(1)
                .isMigrate(false)
                .build();
        department = Department.builder()
                .departmentId(1)
                .departmentName("QA")
                .build();
        company = Company.builder()
                .companyId(1)
                .companyName("Cybage")
                .companyAddress("Pune")
                .build();
        address = Address.builder()
                .addressId(1)
                .localLandmark("Akurdi")
                .homeNumber("302")
                .cityName("pune")
                .pinCode("431601")
                .build();
    }

    @Test
    @DisplayName("Test for User migration by userId migration done with saving other entity.")
    public void givenUserId_whenUserMigrationServiceByUserId_thanReturnMessage_firstCase() {
        BDDMockito.given(userService.getUserByUserId(BDDMockito.anyInt())).willReturn(user);
        BDDMockito.given(companyService.getByCompanyId(BDDMockito.anyInt())).willReturn(company);
        BDDMockito.given(addressService.getAddressById(BDDMockito.anyInt())).willReturn(address);
        BDDMockito.given(departmentService.getDepartmentById(BDDMockito.anyInt())).willReturn(department);
        BDDMockito.given(companyService.getCompanyFromPostgresql(BDDMockito.anyInt())).willReturn(null);
        BDDMockito.given(addressService.getAddressFromPostgresql(BDDMockito.anyInt())).willReturn(null);
        BDDMockito.given(departmentService.getDepartmentFromPostgresql(BDDMockito.anyInt())).willReturn(null);
        BDDMockito.willDoNothing().given(userProducer).sendRecord(BDDMockito.any(User.class));
        BDDMockito.willDoNothing().given(addressProducer).sendRecord(BDDMockito.any(Address.class));
        BDDMockito.willDoNothing().given(companyProducer).sendRecord(BDDMockito.any(Company.class));
        BDDMockito.willDoNothing().given(departmentProducer).sendRecord(BDDMockito.any(Department.class));

        String message = userMigrationService.userMigrationServiceByUserId(user.getUserId());

        Assertions.assertThat(message).isEqualTo("Migrated user : " + user.getUserName());
    }

    @Test
    @DisplayName("Test for User migration by userId user is already migrated..")
    public void givenUserId_whenUserMigrationServiceByUserId_thanReturnMessage_secondCase() {
        BDDMockito.given(userService.getUserByUserId(BDDMockito.anyInt())).willReturn(user);
        UserService.setFlag(user);

        String message = userMigrationService.userMigrationServiceByUserId(user.getUserId());

        Assertions.assertThat(message).isEqualTo("User is already migrated..");
    }

    @Test
    @DisplayName("Test for User migration by userId migration done without saving other entity.")
    public void givenUserId_whenUserMigrationServiceByUserId_thanReturnMessage_thirdCase() {
        BDDMockito.given(userService.getUserByUserId(BDDMockito.anyInt())).willReturn(user);
        BDDMockito.given(companyService.getByCompanyId(BDDMockito.anyInt())).willReturn(company);
        BDDMockito.given(addressService.getAddressById(BDDMockito.anyInt())).willReturn(address);
        BDDMockito.given(departmentService.getDepartmentById(BDDMockito.anyInt())).willReturn(department);
        BDDMockito.given(companyService.getCompanyFromPostgresql(BDDMockito.anyInt())).willReturn(company);
        BDDMockito.given(addressService.getAddressFromPostgresql(BDDMockito.anyInt())).willReturn(address);
        BDDMockito.given(departmentService.getDepartmentFromPostgresql(BDDMockito.anyInt())).willReturn(department);
        BDDMockito.willDoNothing().given(userProducer).sendRecord(BDDMockito.any(User.class));

        String message = userMigrationService.userMigrationServiceByUserId(user.getUserId());

        Assertions.assertThat(message).isEqualTo("Migrated user : " + user.getUserName());
    }

    @Test
    @DisplayName("Test for Migration service by company name All users already migrated")
    public void givenCompanyName_whenMigrationServiceByCompanyName_thanReturnMessage_firstCase() {
        BDDMockito.given(companyService.getCompanyByCompanyName(BDDMockito.anyString())).willReturn(company);
        BDDMockito.given(userService.getAllUsersByCompanyId(BDDMockito.anyInt())).willReturn(List.of());

        String message = userMigrationService.migrationServiceByCompanyName(company.getCompanyName());

        Assertions.assertThat(message).isEqualTo("Already All users migrated of this company....:" + company.getCompanyName());
    }

    @Test
    @DisplayName("Test for Migration service by company name with saving other entity")
    public void givenCompanyName_whenMigrationServiceByCompanyName_thanReturnMessage_secondCase() {
        BDDMockito.given(companyService.getCompanyByCompanyName(BDDMockito.anyString())).willReturn(company);
        BDDMockito.given(userService.getAllUsersByCompanyId(BDDMockito.anyInt())).willReturn(List.of(user));
        BDDMockito.given(companyService.getCompanyFromPostgresql(BDDMockito.anyInt())).willReturn(null);
        BDDMockito.given(addressService.getAddressFromPostgresql(BDDMockito.anyInt())).willReturn(null);
        BDDMockito.given(departmentService.getDepartmentFromPostgresql(BDDMockito.anyInt())).willReturn(null);

        BDDMockito.given(addressService.getAddressById(BDDMockito.anyInt())).willReturn(address);
        BDDMockito.given(departmentService.getDepartmentById(BDDMockito.anyInt())).willReturn(department);

        BDDMockito.willDoNothing().given(userProducer).sendRecord(BDDMockito.any(User.class));
        BDDMockito.willDoNothing().given(addressProducer).sendRecord(BDDMockito.any(Address.class));
        BDDMockito.willDoNothing().given(companyProducer).sendRecord(BDDMockito.any(Company.class));
        BDDMockito.willDoNothing().given(departmentProducer).sendRecord(BDDMockito.any(Department.class));

        String message = userMigrationService.migrationServiceByCompanyName(company.getCompanyName());

        Assertions.assertThat(message).isEqualTo("All users migrated of this company ....:" + company.getCompanyName());
    }

    @Test
    @DisplayName("Test for Migration service by company name without saving other entity")
    public void givenCompanyName_whenMigrationServiceByCompanyName_thanReturnMessage_thirdCase() {
        BDDMockito.given(companyService.getCompanyByCompanyName(BDDMockito.anyString())).willReturn(company);
        BDDMockito.given(userService.getAllUsersByCompanyId(BDDMockito.anyInt())).willReturn(List.of(user));
        BDDMockito.given(companyService.getCompanyFromPostgresql(BDDMockito.anyInt())).willReturn(company);
        BDDMockito.given(addressService.getAddressFromPostgresql(BDDMockito.anyInt())).willReturn(address);
        BDDMockito.given(departmentService.getDepartmentFromPostgresql(BDDMockito.anyInt())).willReturn(department);

        BDDMockito.given(addressService.getAddressById(BDDMockito.anyInt())).willReturn(address);
        BDDMockito.given(departmentService.getDepartmentById(BDDMockito.anyInt())).willReturn(department);
        BDDMockito.willDoNothing().given(userProducer).sendRecord(BDDMockito.any(User.class));

        String message = userMigrationService.migrationServiceByCompanyName(company.getCompanyName());

        Assertions.assertThat(message).isEqualTo("All users migrated of this company ....:" + company.getCompanyName());
    }


}
