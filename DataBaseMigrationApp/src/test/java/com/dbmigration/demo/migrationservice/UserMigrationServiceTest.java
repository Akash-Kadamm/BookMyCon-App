package com.dbmigration.demo.migrationservice;

import com.dbmigration.demo.dto.UserDetailsDTO;
import com.dbmigration.demo.encryptanddecrypt.Encryption;
import com.dbmigration.demo.model.*;
import com.dbmigration.demo.service.*;
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
    @Mock
    private CardDetailService cardDetailService;
    User user;
    Department department;
    Company company;
    Address address;
    CardDetail cardDetail;
    UserDetailsDTO userDetailsDTO;

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
        cardDetail = CardDetail.builder()
                .cardDetailId(1)
                .cardHolderName("Akash Kadam")
                .cardNumber("1234123412341234")
                .build();
        userDetailsDTO = UserDetailsDTO.builder()
                .cardDetail(cardDetail)
                .address(address)
                .department(department)
                .company(company)
                .user(user)
                .build();
    }

    @Test
    @DisplayName("Test for User migration by userId migration done with saving other entity.")
    public void givenUserId_whenUserMigrationServiceByUserId_thanReturnMessage_firstCase() {
        BDDMockito.given(userService.getUserByUserId(BDDMockito.anyInt())).willReturn(user);
        BDDMockito.given(companyService.getByCompanyId(BDDMockito.anyInt())).willReturn(company);
        BDDMockito.given(addressService.getAddressById(BDDMockito.anyInt())).willReturn(address);
        BDDMockito.given(departmentService.getDepartmentById(BDDMockito.anyInt())).willReturn(department);
        BDDMockito.given(cardDetailService.getCardDetailByIdFromMysqlDataBase(BDDMockito.anyInt())).willReturn(cardDetail);

        BDDMockito.given(companyService.getCompanyFromPostgresql(BDDMockito.anyInt())).willReturn(null);
        BDDMockito.given(addressService.getAddressFromPostgresql(BDDMockito.anyInt())).willReturn(null);
        BDDMockito.given(departmentService.getDepartmentFromPostgresql(BDDMockito.anyInt())).willReturn(null);
        BDDMockito.given(cardDetailService.getCardDetailByIdFromPostgresqlDataBase(BDDMockito.anyInt())).willReturn(null);


        BDDMockito.given(userService.saveUserInMysql(BDDMockito.any(User.class))).willReturn(user);
        BDDMockito.given(userService.saveUserInPostgresql(BDDMockito.any(User.class))).willReturn(user);

        BDDMockito.given(companyService.saveCompany(BDDMockito.any(Company.class))).willReturn(company);
        BDDMockito.given(addressService.saveAddress(BDDMockito.any(Address.class))).willReturn(ResponseMessage.ADDRESS_RECORD_SAVED);
        BDDMockito.given(departmentService.saveDepartment(BDDMockito.any(Department.class))).willReturn(ResponseMessage.DEPARTMENT_RECORD_SAVED);
        BDDMockito.given(cardDetailService.saveCardDetails(BDDMockito.any(CardDetail.class))).willReturn(ResponseMessage.CARD_DETAILS_SAVED.getMessage());
        user.setUserPassword(Encryption.encryptionOfPassword(user.getUserPassword()));

        String message = userMigrationService.userMigrationServiceByUserId(user.getUserId());

        Assertions.assertThat(message).isEqualTo("Migrated user : " + user.getUserName());
    }

    @Test
    @DisplayName("Test for User migration by userId user is already migrated..")
    public void givenUserId_whenUserMigrationServiceByUserId_thanReturnMessage_secondCase() {
        BDDMockito.given(userService.getUserByUserId(BDDMockito.anyInt())).willReturn(user);
        UserService.setFlag(user);

        String message = userMigrationService.userMigrationServiceByUserId(user.getUserId());

        Assertions.assertThat(message).isEqualTo(ResponseMessage.USER_ALREADY_MIGRATED.getMessage());
    }

    @Test
    @DisplayName("Test for User migration by userId migration done without saving other entity.")
    public void givenUserId_whenUserMigrationServiceByUserId_thanReturnMessage_thirdCase() {
        BDDMockito.given(userService.getUserByUserId(BDDMockito.anyInt())).willReturn(user);
        BDDMockito.given(companyService.getByCompanyId(BDDMockito.anyInt())).willReturn(company);
        BDDMockito.given(addressService.getAddressById(BDDMockito.anyInt())).willReturn(address);
        BDDMockito.given(departmentService.getDepartmentById(BDDMockito.anyInt())).willReturn(department);
        BDDMockito.given(cardDetailService.getCardDetailByIdFromMysqlDataBase(BDDMockito.anyInt())).willReturn(cardDetail);

        BDDMockito.given(companyService.getCompanyFromPostgresql(BDDMockito.anyInt())).willReturn(company);
        BDDMockito.given(addressService.getAddressFromPostgresql(BDDMockito.anyInt())).willReturn(address);
        BDDMockito.given(departmentService.getDepartmentFromPostgresql(BDDMockito.anyInt())).willReturn(department);
        BDDMockito.given(cardDetailService.saveCardDetails(BDDMockito.any(CardDetail.class))).willReturn(ResponseMessage.CARD_DETAILS_SAVED.getMessage());
        BDDMockito.given(userService.saveUserInMysql(BDDMockito.any(User.class))).willReturn(user);
        BDDMockito.given(userService.saveUserInPostgresql(BDDMockito.any(User.class))).willReturn(user);

        String message = userMigrationService.userMigrationServiceByUserId(user.getUserId());

        Assertions.assertThat(message).isEqualTo("Migrated user : " + user.getUserName());
    }

    @Test
    @DisplayName("Test for Migration service by company name All users already migrated")
    public void givenCompanyName_whenMigrationServiceByCompanyName_thanReturnMessage_firstCase() {
        BDDMockito.given(companyService.getCompanyByCompanyName(BDDMockito.anyString())).willReturn(company);
        BDDMockito.given(userService.getAllUsersByCompanyId(BDDMockito.anyInt())).willReturn(List.of());

        String message = userMigrationService.migrationServiceByCompanyName(company.getCompanyName());

        Assertions.assertThat(message).isEqualTo("Already All users migrated of this company. :" + company.getCompanyName());
    }

    @Test
    @DisplayName("Test for Migration service by company name with saving other entity")
    public void givenCompanyName_whenMigrationServiceByCompanyName_thanReturnMessage_secondCase() {
        BDDMockito.given(companyService.getCompanyByCompanyName(BDDMockito.anyString())).willReturn(company);
        BDDMockito.given(userService.getAllUsersByCompanyId(BDDMockito.anyInt())).willReturn(List.of(user));
        BDDMockito.given(companyService.getCompanyFromPostgresql(BDDMockito.anyInt())).willReturn(null);
        BDDMockito.given(addressService.getAddressFromPostgresql(BDDMockito.anyInt())).willReturn(null);
        BDDMockito.given(departmentService.getDepartmentFromPostgresql(BDDMockito.anyInt())).willReturn(null);
        BDDMockito.given(cardDetailService.getCardDetailByIdFromPostgresqlDataBase(BDDMockito.anyInt())).willReturn(null);
        BDDMockito.given(addressService.getAddressById(BDDMockito.anyInt())).willReturn(address);
        BDDMockito.given(cardDetailService.getCardDetailByIdFromMysqlDataBase(BDDMockito.anyInt())).willReturn(cardDetail);
        BDDMockito.given(departmentService.getDepartmentById(BDDMockito.anyInt())).willReturn(department);

        BDDMockito.given(userService.saveUserInMysql(BDDMockito.any(User.class))).willReturn(user);
        BDDMockito.given(userService.saveUserInPostgresql(BDDMockito.any(User.class))).willReturn(user);

        BDDMockito.given(companyService.saveCompany(BDDMockito.any(Company.class))).willReturn(company);
        BDDMockito.given(addressService.saveAddress(BDDMockito.any(Address.class))).willReturn(ResponseMessage.ADDRESS_RECORD_SAVED);
        BDDMockito.given(departmentService.saveDepartment(BDDMockito.any(Department.class))).willReturn(ResponseMessage.DEPARTMENT_RECORD_SAVED);
        BDDMockito.given(cardDetailService.saveCardDetails(BDDMockito.any(CardDetail.class))).willReturn(ResponseMessage.CARD_DETAILS_SAVED.getMessage());
        user.setUserPassword(Encryption.encryptionOfPassword(user.getUserPassword()));

        String message = userMigrationService.migrationServiceByCompanyName(company.getCompanyName());

        Assertions.assertThat(message).isEqualTo("All users migrated of this company. :" + company.getCompanyName());
    }

    @Test
    @DisplayName("Test for Migration service by company name without saving other entity")
    public void givenCompanyName_whenMigrationServiceByCompanyName_thanReturnMessage_thirdCase() {
        BDDMockito.given(companyService.getCompanyByCompanyName(BDDMockito.anyString())).willReturn(company);
        BDDMockito.given(userService.getAllUsersByCompanyId(BDDMockito.anyInt())).willReturn(List.of(user));
        BDDMockito.given(companyService.getCompanyFromPostgresql(BDDMockito.anyInt())).willReturn(company);
        BDDMockito.given(addressService.getAddressFromPostgresql(BDDMockito.anyInt())).willReturn(address);
        BDDMockito.given(departmentService.getDepartmentFromPostgresql(BDDMockito.anyInt())).willReturn(department);
        BDDMockito.given(cardDetailService.getCardDetailByIdFromMysqlDataBase(BDDMockito.anyInt())).willReturn(cardDetail);

        BDDMockito.given(cardDetailService.saveCardDetails(BDDMockito.any(CardDetail.class))).willReturn(ResponseMessage.CARD_DETAILS_SAVED.getMessage());

        BDDMockito.given(addressService.getAddressById(BDDMockito.anyInt())).willReturn(address);
        BDDMockito.given(departmentService.getDepartmentById(BDDMockito.anyInt())).willReturn(department);

        BDDMockito.given(userService.saveUserInMysql(BDDMockito.any(User.class))).willReturn(user);
        BDDMockito.given(userService.saveUserInPostgresql(BDDMockito.any(User.class))).willReturn(user);

        String message = userMigrationService.migrationServiceByCompanyName(company.getCompanyName());

        Assertions.assertThat(message).isEqualTo("All users migrated of this company. :" + company.getCompanyName());
    }

    @Test
    @DisplayName("Test for fetch all user details from postgresql database.")
    public void givenUserId_whenFetchAllUserDetails_thanReturnUserDetailsDto() {
        BDDMockito.given(userService.getUserFromPostgresql(BDDMockito.anyInt())).willReturn(user);
        BDDMockito.given(addressService.getAddressFromPostgresql(BDDMockito.anyInt())).willReturn(address);
        BDDMockito.given(companyService.getCompanyFromPostgresql(BDDMockito.anyInt())).willReturn(company);
        BDDMockito.given(departmentService.getDepartmentFromPostgresql(BDDMockito.anyInt())).willReturn(department);
        BDDMockito.given(cardDetailService.getCardDetailByIdFromPostgresqlDataBase(BDDMockito.anyInt())).willReturn(cardDetail);

        UserDetailsDTO savedUserDetailsDTO = userMigrationService.fetchAllUserDetails(user.getUserId());
        Assertions.assertThat(savedUserDetailsDTO).isNotNull();
    }

    @Test
    @DisplayName("Test for Delete all user details from postgresql database Positive scenario.")
    public void givenUserId_whenDeleteAllDetailsOfUser_thanReturnPositiveMessage() {
        BDDMockito.given(userService.getUserFromPostgresql(BDDMockito.anyInt())).willReturn(user);
        BDDMockito.given(departmentService.deleteDepartment(BDDMockito.anyInt()))
                .willReturn(ResponseMessage.DEPARTMENT_RECORD_DELETED.getMessage());
        BDDMockito.given(addressService.deleteAddress(BDDMockito.anyInt()))
                .willReturn(ResponseMessage.ADDRESS_RECORD_DELETED.getMessage());
        BDDMockito.given(cardDetailService.deleteCardDetail(BDDMockito.anyInt()))
                .willReturn(ResponseMessage.CARD_DETAILS_ARE_DELETED.getMessage());
        BDDMockito.given(userService.deleteUser(BDDMockito.anyInt()))
                .willReturn(ResponseMessage.USER_DELETED.getMessage());

        String message = userMigrationService.deleteAllDetailsOfUser(user.getUserId());

        Assertions.assertThat(message).isEqualTo(ResponseMessage.USER_DELETED.getMessage());
    }

    @Test
    @DisplayName("Test for Delete all User details from postgresql database Negative scenario.")
    public void givenUserId_whenDeleteAllDetailsOfUser_thanReturnNegativeMessage() {
        BDDMockito.given(userService.getUserFromPostgresql(BDDMockito.anyInt())).willReturn(null);

        String message = userMigrationService.deleteAllDetailsOfUser(user.getUserId());

        Assertions.assertThat(message).isEqualTo(ResponseMessage.USER_NOT_FOUND.getMessage());
    }

}
