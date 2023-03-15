package com.dbmigration.demo.service;

import com.dbmigration.demo.model.Department;
import com.dbmigration.demo.repo.mysql.MysqlUserRepo;
import com.dbmigration.demo.model.User;
import com.dbmigration.demo.repo.postgresql.PostgresqlUserRepo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private MysqlUserRepo mysqlUserRepo;
    @Autowired
    private PostgresqlUserRepo postgresqlUserRepo;

    private static Logger logger=Logger.getLogger(UserService.class);

    /*
    * Fetch All users from mysql Database.
    *
    * @param
    * @return List of Users
    * */
    public List<User> getAllUsers() {
     return  mysqlUserRepo.findAll();
    }

    /*
    * Save User in Mysql Database.
    *
    * @param User
    * @return User
    * */
    public User saveUserInMysql(User user){
        return mysqlUserRepo.save(user);
    }

    /*
    * Save new User in postgresql Database.
    *
    * @param User
    * @return User
    * */
    public User saveUserInPostgresql(User user){
        return postgresqlUserRepo.save(user);
    }

   /*
   * Delete User by its userId from Mysql Database.
   *
   * @param user Id.
   * @return String message
   * */
    public String deleteUser(int userId){
        mysqlUserRepo.deleteById(userId);
        return "User is deleted....";
    }

   /*
   * User update from Mysql Database.
   *
   * @param User
   * @return String message
   * */
    public String updateUser(User user){
        User savedUser=mysqlUserRepo.findById(user.getUserId()).get();
        savedUser.setUserName(user.getUserName());
        savedUser.setUserPassword(user.getUserPassword());
        savedUser.setUserContactNumber(user.getUserContactNumber());
        mysqlUserRepo.save(savedUser);
        return "User is Updated....";
    }

    /*
    * Fetch User by its user ID.
    *
    * @param user Id.
    * @return User
    * */
    public User getUserByUserId(int userId){
        return mysqlUserRepo.findById(userId).get();
    }

    /*
    * Set migrated flag to true.
    *
    * @parma User
    * @return void
    * */
    public static void setFlag(User user){
        user.setMigrate(true);
    }

    /*
    * Fetch all users to be migrating by its company id.
    *
    * @param company Id.
    * @return  List of Users
    * */
    public List<User> getAllUsersByCompanyId(int companyId){
        List<User> users=mysqlUserRepo.fetchUsersByCompanyId(companyId);
        if(users== null){
            logger.info("getting null users.....");
        }else{
            logger.info("Fetching Users....");
            users.forEach(user->{
                logger.info("user details : "+user.toString());
            });
        }
        return users;
    }
}
