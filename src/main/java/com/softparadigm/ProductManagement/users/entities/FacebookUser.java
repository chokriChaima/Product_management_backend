package com.softparadigm.ProductManagement.users.entities;


import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Random;

@Document(collection = "facebook-users")
public class FacebookUser extends LocalUser {


    private String facebookId;


    public FacebookUser() {
        super();

    }

    @Override
    public String toString() {
        return "FacebookUser{" +
                super.toString()+
                "facebookId='" + facebookId + '\'' +
                '}';
    }

    public void generateAndSetPassword() {
        //TO-DO
        String uppercaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowercaseLetters = "abcdefghijklmnopqrstuvwxyz";
        String numbers = "0123456789";
        String symbols = "!@#$%^&*()_+-=[]{}|;':\"<>,.?/";

        int length = 10;


        String allCharacters = uppercaseLetters + lowercaseLetters + numbers + symbols;
        Random random = new Random();

        char[] password = new char[length];
        for (int i = 0; i < length; i++) {
            password[i] = allCharacters.charAt(random.nextInt(allCharacters.length()));
        }
        setPassword(new String(password));
    }

    public String getFacebookId() {
        return facebookId;
    }

    public void setFacebookId(String facebookId) {
        this.facebookId = facebookId;
    }
}
