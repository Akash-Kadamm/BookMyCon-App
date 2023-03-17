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
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    private UserProducer userProducer;
    @Autowired
    private AddressProducer addressProducer;
    @Autowired
    private DepartmentProducer departmentProducer;
    @Autowired
    private CompanyProducer companyProducer;


    /*
     * Migrate service for migrate User by its UserId.
     *
     * @param userId.
     * @return String message
     * */
    public String userMigrationServiceByUserId(int userId) {
        User user = userService.getUserByUserId(userId);

        if (user.isMigrate() == false) {
            Company company = companyService.getByCompanyId(user.getCompanyId());
            Address address = addressService.getAddressById(user.getAddressId());
            Department department = departmentService.getDepartmentById(user.getDepartmentId());

            Company migratedCompany = companyService.getCompanyFromPostgresql(user.getCompanyId());
            if (migratedCompany == null) {
                companyProducer.sendRecord(company);
                logger.info("Company event send....:" + company.toString());
            }
            logger.info("Company is already present..." + company.toString());

            Address migratedAddress = addressService.getAddressFromPostgresql(user.getAddressId());
            if (migratedAddress == null) {
                addressProducer.sendRecord(address);
                logger.info("Address event send...." + address.toString());
            }
            logger.info("Address is already present..." + address.toString());

            Department migratedDepartment = departmentService.getDepartmentFromPostgresql(user.getDepartmentId());
            if (migratedDepartment == null) {
                departmentProducer.sendRecord(department);
                logger.info("Department event send...." + department.toString());
            }
            logger.info("Department is already present..." + department.toString());

            UserService.setFlag(user);
            userProducer.sendRecord(user);
            return "Migrated user : " + user.getUserName();
        } else {
            return "User is already migrated..";
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
                companyProducer.sendRecord(company);
                logger.info("Company event send...:" + company.toString());
            }
            logger.info("Company is already present...." + company.toString());
            // filter record according to same department id.--- lets see after basic solution.

            userList.forEach((user) -> {

                Address address = addressService.getAddressById(user.getAddressId());
                Address migratedAddress = addressService.getAddressFromPostgresql(user.getAddressId());
                if (migratedAddress == null) {
                    addressProducer.sendRecord(address);
                    logger.info("Address event send....:" + address.toString());
                }
                logger.info("Address is already present...: " + address.toString());

                Department department = departmentService.getDepartmentById(user.getDepartmentId());
                Department migratedDepartment = departmentService.getDepartmentFromPostgresql(user.getDepartmentId());
                if (migratedDepartment == null) {
                    departmentProducer.sendRecord(department);
                    logger.info("Department event send...." + department.toString());
                }
                logger.info("Department is already present..." + department.toString());

                UserService.setFlag(user);
                userProducer.sendRecord(user);

            });
            return "All users migrated of this company ....:" + company.getCompanyName();
        } else {
            return "Already All users migrated of this company....:" + company.getCompanyName();
        }

    }
}
