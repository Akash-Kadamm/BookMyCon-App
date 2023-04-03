package com.dbMigration.demo.utility;

import java.util.Base64;

public class Decryption {

    public static String decryptionOfPassword(String password) {
        byte[] encryptedByte = password.getBytes();
        byte[] decryptedPassword = Base64.getDecoder().decode(encryptedByte);
        System.out.println("Decrypted password is :" + new String(decryptedPassword));
        return new String(decryptedPassword);
    }

    public static String decryptionOfCardNumber(String cardNumber) {
        byte[] encryptedByte=cardNumber.getBytes();
        byte[] decryptedCardNumber=Base64.getDecoder().decode(encryptedByte);
        System.out.println("Decrypted card details :"+new String(decryptedCardNumber));
        return new String(decryptedCardNumber);
    }
}
