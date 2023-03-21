package com.dbmigration.demo.migrationservice;

import com.dbmigration.demo.dto.UserDetailsDTO;
import com.dbmigration.demo.encryptanddecrypt.Encryption;
import com.dbmigration.demo.model.*;
import com.dbmigration.demo.service.*;
import com.dbmigration.demo.utility.ResponseMessage;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserMigrationService {

    private static Logger logger = Logger.getLogger(UserMigrationService.class);
    @Autowired
    private UserService userService;
    @Autowired
    private CompanyService companyService;
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private AddressService addressService;

    @Autowired
    private CardDetailService cardDetailService;

    /*
     * Migrate service for migrate User by its UserId.
     *
     * @param userId.
     * @return String message
     * */
    public String userMigrationServiceByUserId(int userId) {
        logger.info("Enter in migration service by userId");
        User user = userService.getUserByUserId(userId);
        System.out.println(user.toString());

        if (user.isMigrate() == false) {
            Company company = companyService.getByCompanyId(user.getCompanyId());
            Address address = addressService.getAddressById(user.getAddressId());
            Department department = departmentService.getDepartmentById(user.getDepartmentId());
            CardDetail cardDetail = cardDetailService.getCardDetailByIdFromMysqlDataBase(user.getCardDetailId());

            Company migratedCompany = companyService.getCompanyFromPostgresql(user.getCompanyId());
            if (migratedCompany == null) {
                companyService.saveCompany(company);
                logger.info("Company saving. :" + company.toString());
            }


            Address migratedAddress = addressService.getAddressFromPostgresql(user.getAddressId());
            if (migratedAddress == null) {
                addressService.saveAddress(address);
                logger.info("Address is saving. " + address.toString());
            }

            CardDetail migratedCardDetail = cardDetailService.getCardDetailByIdFromPostgresqlDataBase(user.getCardDetailId());
            if (migratedCardDetail == null) {
                cardDetailService.saveCardDetails(cardDetail);
                logger.info("Card Detail saving. " + cardDetail.toString());
            }

            Department migratedDepartment = departmentService.getDepartmentFromPostgresql(user.getDepartmentId());
            if (migratedDepartment == null) {
                departmentService.saveDepartment(department);
                logger.info("Department is saving. " + department.toString());
            }

            UserService.setFlag(user);
            // Password Encryption
            user.setUserPassword(Encryption.encryptionOfPassword(user.getUserPassword()));
            userService.saveUserInMysql(user);
            userService.saveUserInPostgresql(user);
            return "Migrated user : " + user.getUserName();
        } else {
            return ResponseMessage.USER_ALREADY_MIGRATED.getMessage();
        }
    }

    /*
     * Migrate service for migrate Users According to company name.
     *
     * @param String companyName
     * @return String message
     * */
    public String migrationServiceByCompanyName(String companyName) {
        logger.info(" migration start of company :" + companyName);
        Company company = companyService.getCompanyByCompanyName(companyName);
        List<User> userList = userService.getAllUsersByCompanyId(company.getCompanyId());

        if (userList.size() != 0) {
            Company migratedCompany = companyService.getCompanyFromPostgresql(company.getCompanyId());
            if (migratedCompany == null) {
                companyService.saveCompany(company);
                logger.info("Company is saving. :" + company.toString());
            }
            // filter record according to same department id.--- lets see after basic solution.
            // for optimze and improve readability we can combine if conditions.

            userList.forEach((user) -> {

                Address address = addressService.getAddressById(user.getAddressId());
                Address migratedAddress = addressService.getAddressFromPostgresql(user.getAddressId());
                if (migratedAddress == null) {
                    addressService.saveAddress(address);
                    logger.info("Address is saving. :" + address.toString());
                }

                CardDetail cardDetail = cardDetailService.getCardDetailByIdFromMysqlDataBase(user.getCardDetailId());
                CardDetail migratedCardDetail = cardDetailService.getCardDetailByIdFromPostgresqlDataBase(user.getCardDetailId());
                if (migratedCardDetail == null) {
                    cardDetailService.saveCardDetails(cardDetail);
                    logger.info("Card Detail saving. " + cardDetail.toString());
                }

                Department department = departmentService.getDepartmentById(user.getDepartmentId());
                Department migratedDepartment = departmentService.getDepartmentFromPostgresql(user.getDepartmentId());
                if (migratedDepartment == null) {
                    departmentService.saveDepartment(department);
                    logger.info("Department is saving. " + department.toString());
                }

                UserService.setFlag(user);
                // Password Encryption
                user.setUserPassword(Encryption.encryptionOfPassword(user.getUserPassword()));
                userService.saveUserInMysql(user);
                userService.saveUserInPostgresql(user);

            });
            return "All users migrated of this company. :" + company.getCompanyName();
        } else {
            return "Already All users migrated of this company. :" + company.getCompanyName();
        }

    }


    public UserDetailsDTO fetchAllUserDetails(int userId) {
        User user = userService.getUserFromPostgresql(userId);
        Address address = addressService.getAddressFromPostgresql(user.getAddressId());
        Company company = companyService.getCompanyFromPostgresql(user.getCompanyId());
        Department department = departmentService.getDepartmentFromPostgresql(user.getDepartmentId());
        CardDetail cardDetail = cardDetailService.getCardDetailByIdFromPostgresqlDataBase(user.getCardDetailId());

        cardDetail.setCardNumber(Encryption.maskedCardNumber(cardDetail.getCardNumber()));

        UserDetailsDTO userDetailsDTO = UserDetailsDTO.builder()
                .cardDetail(cardDetail)
                .address(address)
                .department(department)
                .company(company)
                .user(user)
                .build();

        return userDetailsDTO;
    }

    public String deleteAllDetailsOfUser(int userId) {
        User user = userService.getUserFromPostgresql(userId);
        if (user == null) {
            return ResponseMessage.USER_NOT_FOUND.getMessage();
        }
        departmentService.deleteDepartment(user.getDepartmentId());
        addressService.deleteAddress(user.getAddressId());
        cardDetailService.deleteCardDetail(user.getCardDetailId());
        userService.deleteUser(userId);
        return ResponseMessage.USER_DELETED.getMessage();
    }

}
