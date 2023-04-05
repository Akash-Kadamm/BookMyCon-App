package com.dbMigration.demo.utility;


import java.util.Base64;

public class Encryption {


    public static String encryptionOfPassword(String password) {
        byte[] encryptedPassword = Base64.getEncoder().encode(password.getBytes());
        System.out.println("Password Is Encrypted :" + new String(encryptedPassword));
        return new String(encryptedPassword);
    }

    public static String encryptCardNumber(String cardNumber) {
        byte[] encryptedCardNumber=Base64.getEncoder().encode(cardNumber.getBytes());
        System.out.println("Card number is encrypted :"+new String(encryptedCardNumber));
        return new String(encryptedCardNumber);
    }

    public static String maskedCardNumber(String cardNumber){
        String maskedCardNumber="**** **** **** "+cardNumber.substring(12);
        return maskedCardNumber;
    }
}
