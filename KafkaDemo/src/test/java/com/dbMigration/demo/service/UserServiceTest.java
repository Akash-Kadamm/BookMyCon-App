package com.dbMigration.demo.service;

import com.dbMigration.demo.mysql.MysqlUserRepo;
import com.dbMigration.demo.payload.User;
import com.dbMigration.demo.postgresql.PostgresqlUserRepo;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private MysqlUserRepo mysqlUserRepo;

    @Mock
    private PostgresqlUserRepo postgresqlUserRepo;

    @InjectMocks
    private UserService userService;

    User user1;

    User user2;

    @BeforeEach
    public void setUp(){
        user1=User.builder()
                .userId(1)
                .userEmail("akashkad@cybage.com")
                .userName("Akash Kadam")
                .isMigrate(false)
                .userPassword("ak@123")
                .userContactNumber("7038967693")
                .build();
        user2=User.builder()
                .userId(2)
                .userEmail("akshayshe@cybage.com")
                .userName("Akshay Shende")
                .isMigrate(false)
                .userPassword("ak@123")
                .userContactNumber("7039967693")
                .build();
    }

    @Test
    @DisplayName("Test for get all users")
    public void givenListOfUsers_whenGetAllUsers_thanListOfUsers(){
            BDDMockito.given(mysqlUserRepo.findAll()).willReturn(List.of(user1,user2));
            List<User> savedUsers=userService.getAllUsers();
            Assertions.assertThat(savedUsers.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("Test for save user in Mysql database")
    public void givenUserObject_whenSaveInMysql_thanUserObject(){
        BDDMockito.given(mysqlUserRepo.save(user1)).willReturn(user1);
        User savedUsers=userService.saveUserInMysql(user1);
        Assertions.assertThat(savedUsers.getUserId()).isEqualTo(1);
        Assertions.assertThat(savedUsers.getUserName()).isEqualTo("Akash Kadam");
    }

    @Test
    @DisplayName("Test for save user in Postgresql database")
    public void givenUserObject_whenSaveInPostgresql_thanUserObject(){
        BDDMockito.given(postgresqlUserRepo.save(user1)).willReturn(user1);
        User savedUsers=userService.saveUserInPostgresql(user1);
        Assertions.assertThat(savedUsers.getUserId()).isEqualTo(1);
        Assertions.assertThat(savedUsers.getUserName()).isEqualTo("Akash Kadam");
    }

    @Test
    @DisplayName("Test for Delete User from Mysql DataBase.")
    public void givenUserId_whenDeleteUser_thanMessage(){
        int userId=1;
        BDDMockito.willDoNothing().given(mysqlUserRepo).deleteById(userId);
        String message=userService.deleteUser(userId);
        BDDMockito.verify(mysqlUserRepo, Mockito.times(1)).deleteById(BDDMockito.anyInt());
        Assertions.assertThat(message).isEqualTo("User is deleted....");
    }

    @Test
    @DisplayName("Test for Update User")
    public void givenUserObject_whenUpdateUser_thanMessage(){
        BDDMockito.given(mysqlUserRepo.findById(BDDMockito.anyInt())).willReturn(Optional.of(user1));
        BDDMockito.given(mysqlUserRepo.save(user1)).willReturn(user1);
        String message=userService.updateUser(user1);
        Assertions.assertThat(message).isEqualTo("User is Updated....");
    }

    @Test
    @DisplayName("Test for Fetch User By userId")
    public void givenUserObject_whenGetUserByUserId_thanUserObject(){
        int userId=1;
        BDDMockito.given(mysqlUserRepo.findById(BDDMockito.anyInt())).willReturn(Optional.of(user1));
        User savedUser=userService.getUserByUserId(userId);
        Assertions.assertThat(savedUser.getUserId()).isEqualTo(1);
        Assertions.assertThat(savedUser.getUserName()).isEqualTo("Akash Kadam");
    }
    @Test
    @DisplayName("Test for Set flag.")
    public void givenUser_whenSetFlag_thanReturnNothing(){
        UserService.setFlag(user1);
        Assertions.assertThat(user1.isMigrate()).isEqualTo(true);
    }

    @Test
    @DisplayName("Test for Get all users by company id (positive)")
    public void givenCompanyId_whenGetAllUsersByCompanyId_thanListUsers(){
        int companyId=1;
        BDDMockito.given(mysqlUserRepo.fetchUsersByCompanyId(BDDMockito.anyInt())).willReturn(List.of(user1,user2));
        List<User> savedUsers=userService.getAllUsersByCompanyId(companyId);
        Assertions.assertThat(savedUsers.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("Test for Get all users by company id (negative)")
    public void givenCompanyId_whenGetAllUsersByCompanyId_thanNull(){
        int companyId=1;
        BDDMockito.given(mysqlUserRepo.fetchUsersByCompanyId(BDDMockito.anyInt())).willReturn(null);
        List<User> savedUsers=userService.getAllUsersByCompanyId(companyId);
        Assertions.assertThat(savedUsers).isEqualTo(null);
    }

}
