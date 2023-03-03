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


    public String userMigrationServiceByUserId(int userId){
       User savedUser= userService.getUserByUserId(userId);
       User user=User.builder()
               .userId(savedUser.getUserId())
               .userPassword(savedUser.getUserPassword())
               .userName(savedUser.getUserName())
               .userEmail(savedUser.getUserEmail())
               .userContactNumber(savedUser.getUserContactNumber())
               .address(savedUser.getAddress())
               .department(savedUser.getDepartment())
               .company(savedUser.getCompany())
               .build();
       userService.saveUserInPostgresql(user);
       addressService.saveAddress(savedUser.getAddress());
       departmentService.saveDepartment(savedUser.getDepartment());
       companyService.saveCompany(savedUser.getCompany());

       return "Migrated User having userId : "+userId;
    }

    public String migrationServiceByCompanyName(String companyName){
        List<Company> listOfUsers=companyService.getAllUsersToBeMigrate(companyName);

        return "";
    }


}
