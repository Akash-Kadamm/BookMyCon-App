package com.dbMigration.demo.service;

import com.dbMigration.demo.mysql.MysqlUserRepo;
import com.dbMigration.demo.payload.User;
import com.dbMigration.demo.postgresql.PostgresqlUserRepo;
import com.dbMigration.demo.utility.ResponseMessage;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private MysqlUserRepo mysqlUserRepo;
    @Autowired
    private PostgresqlUserRepo postgresqlUserRepo;

    private static Logger logger = Logger.getLogger(UserService.class);

    /*
     * Fetch All users from mysql Database.
     *
     * @param
     * @return List of Users
     * */
    public List<User> getAllUsers() {
        return mysqlUserRepo.findAll();
    }

    /*
     * Save User in Mysql Database.
     *
     * @param User
     * @return User
     * */
    public User saveUserInMysql(User user) {
        return mysqlUserRepo.save(user);
    }

    /*
     * Save new User in postgresql Database.
     *
     * @param User
     * @return User
     * */
    public User saveUserInPostgresql(User user) {
        return postgresqlUserRepo.save(user);
    }

    /*
     * Delete User by its userId from Mysql Database.
     *
     * @param user Id.
     * @return String message
     * */
    public String deleteUser(int userId) {
        mysqlUserRepo.deleteById(userId);
        return "User is deleted....";
    }

    /*
     * User update from Mysql Database.
     *
     * @param User
     * @return String message
     * */
    public String updateUser(User user) {
        Optional<User> savedUser = mysqlUserRepo.findById(user.getUserId());
        if(savedUser.isEmpty()){
            return ResponseMessage.USER_NOT_FOUND.getMessage();
        }
        savedUser.get().setUserName(user.getUserName());
        savedUser.get().setUserPassword(user.getUserPassword());
        savedUser.get().setUserContactNumber(user.getUserContactNumber());
        mysqlUserRepo.save(savedUser.get());
        return ResponseMessage.USER_UPDATED.getMessage();
    }

    /*
     * Fetch User by its user ID.
     *
     * @param user Id.
     * @return User
     * */
    public User getUserByUserId(int userId) {
        Optional<User> savedUser = mysqlUserRepo.findById(userId);
        if (savedUser.isEmpty()) {
            return null;
        }
        return savedUser.get();
    }

    /*
     * Set migrated flag to true.
     *
     * @parma User
     * @return void
     * */
    public static void setFlag(User user) {
        user.setMigrate(true);
    }

    /*
     * Fetch all users to be migrating by its company id.
     *
     * @param company Id.
     * @return  List of Users
     * */
    public List<User> getAllUsersByCompanyId(int companyId) {
        List<User> users = mysqlUserRepo.fetchUsersByCompanyId(companyId);
        if (users == null) {
            logger.info("getting null users.....");
        } else {
            logger.info("Fetching Users....");
            users.forEach(user -> {
                logger.info("user details : " + user.toString());
            });
        }
        return users;
    }

    public User getUserFromPostgresql(int userId) {
        Optional<User> savedUser=postgresqlUserRepo.findById(userId);
        if(savedUser.isEmpty()){
            return null;
        }
        return savedUser.get();
    }

}
