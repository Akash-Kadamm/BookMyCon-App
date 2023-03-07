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
            int companyId = user.getCompanyId();
            Company company = companyService.getByCompanyId(companyId);
            UserService.setFlag(user);
            companyService.saveCompany(company);
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
        Company company=companyService.getCompanyByCompanyName(companyName);
        List<User> userList=userService.getAllUsersByCompanyId(company.getCompanyId());
        companyService.saveCompany(company);
        userList.forEach((user)->{
            UserService.setFlag(user);
            userService.saveUserInMysql(user);
            userService.saveUserInPostgresql(user);
        });
        return "All users migrated....";
    }


}
