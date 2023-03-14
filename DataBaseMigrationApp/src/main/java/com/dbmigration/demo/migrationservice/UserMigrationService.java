package com.dbmigration.demo.migrationservice;

import com.dbmigration.demo.model.Address;
import com.dbmigration.demo.model.Company;
import com.dbmigration.demo.model.Department;
import com.dbmigration.demo.model.User;
import com.dbmigration.demo.service.AddressService;
import com.dbmigration.demo.service.CompanyService;
import com.dbmigration.demo.service.DepartmentService;
import com.dbmigration.demo.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserMigrationService {

    private static Logger logger=Logger.getLogger(UserMigrationService.class);
    @Autowired
    private UserService userService;
    @Autowired
    private CompanyService companyService;
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private AddressService addressService;

    /*
    * Migrate service for migrate User by its UserId.
    *
    * @param userId.
    * @return String message
    * */
    public String userMigrationServiceByUserId(int userId){
        User user=userService.getUserByUserId(userId);

        if(user.isMigrate() == false ) {
            Company company = companyService.getByCompanyId(user.getCompanyId());
            Address address=addressService.getAddressById(user.getAddressId());
            Department department=departmentService.getDepartmentById(user.getDepartmentId());

            Company migratedCompany=companyService.getCompanyFromPostgresql(user.getCompanyId());
            if(migratedCompany == null){
                companyService.saveCompany(company);
                logger.info("Company saving....:"+company.toString());
            }
            logger.info("Company is already present..."+company.toString());

            Address migratedAddress=addressService.getAddressFromPostgresql(user.getAddressId());
            if(migratedAddress == null){
                addressService.saveAddress(address);
                logger.info("Address is saving...."+address.toString());
            }
            logger.info("Address is already present..."+address.toString());

            Department migratedDepartment=departmentService.getDepartmentFromPostgresql(user.getDepartmentId());
            if(migratedDepartment == null){
                departmentService.saveDepartment(department);
                logger.info("Department is saving...."+department.toString());
            }
            logger.info("Department is already present..."+department.toString());

            UserService.setFlag(user);
            userService.saveUserInMysql(user);
            userService.saveUserInPostgresql(user);
            return "Migrated user : " + user.getUserName();
        }else {
            return "User is already migrated..";
        }
    }

    /*
    * Migrate service for migrate Users According to company name.
    *
    * @param String companyName
    * @return String message
    * */
    public String migrationServiceByCompanyName(String companyName){
        logger.info(" migration start of company :"+companyName);
        Company company=companyService.getCompanyByCompanyName(companyName);
        List<User> userList=userService.getAllUsersByCompanyId(company.getCompanyId());

        if(userList.size() != 0) {
            Company migratedCompany = companyService.getCompanyFromPostgresql(company.getCompanyId());
            if (migratedCompany == null) {
                companyService.saveCompany(company);
                logger.info("Company is saving...:" + company.toString());
            }
            logger.info("Company is already present...." + company.toString());
            // filter record according to same department id.--- lets see after basic solution.

            userList.forEach((user) -> {

                Address address = addressService.getAddressById(user.getAddressId());
                Address migratedAddress = addressService.getAddressFromPostgresql(user.getAddressId());
                if (migratedAddress == null) {
                    addressService.saveAddress(address);
                    logger.info("Address is saving....:" + address.toString());
                }
                logger.info("Address is already present...: " + address.toString());

                Department department = departmentService.getDepartmentById(user.getDepartmentId());
                Department migratedDepartment = departmentService.getDepartmentFromPostgresql(user.getDepartmentId());
                if (migratedDepartment == null) {
                    departmentService.saveDepartment(department);
                    logger.info("Department is saving...." + department.toString());
                }
                logger.info("Department is already present..."+department.toString());

                UserService.setFlag(user);
                userService.saveUserInMysql(user);
                userService.saveUserInPostgresql(user);

            });
            return "All users migrated of this company ....:"+company.getCompanyName();
        }else {
            return "Already All users migrated of this company....:"+company.getCompanyName();
        }

    }


}
