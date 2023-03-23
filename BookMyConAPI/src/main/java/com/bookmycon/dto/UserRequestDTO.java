package com.bookmycon.dto;

import com.bookmycon.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.web.multipart.MultipartFile;

public class UserRequestDTO {

    private int userId;
    private String userName;
    private String userEmail;
    private String userPassword;
    private String userRole;
    private String userContact;
    private MultipartFile thumbnail;

    public UserRequestDTO() {
    }

    public UserRequestDTO(int userId, String userName, String userEmail, String userPassword, String userRole, String userContact, MultipartFile thumbnail) {
        this.userId = userId;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userRole = userRole;
        this.userContact = userContact;
        this.thumbnail = thumbnail;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public String getUserContact() {
        return userContact;
    }

    public void setUserContact(String userContact) {
        this.userContact = userContact;
    }

    public MultipartFile getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(MultipartFile thumbnail) {
        this.thumbnail = thumbnail;
    }

    @Override
    public String toString() {
        return "UserRequestDTO{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userRole='" + userRole + '\'' +
                ", userContact='" + userContact + '\'' +
                ", thumbnail=" + thumbnail +
                '}';
    }

    public static User toEntity(UserRequestDTO dto) {
        User entity = new User();
        BeanUtils.copyProperties(dto, entity, "thumbnail");
        return entity;
    }
}
