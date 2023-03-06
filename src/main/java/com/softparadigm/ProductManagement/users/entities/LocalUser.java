package com.softparadigm.ProductManagement.users.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "local-users")
public  class LocalUser {
    @Override
    public String toString() {
        return "LocalUser{" +
                "userId='" + userId + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", shoppingCartID='" + shoppingCartID + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Id
    String userId ;
    String name ;
    String email;
    String shoppingCartID;
    private String password;

    public LocalUser() {

    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setShoppingCartID(String shoppingCartID) {
        this.shoppingCartID = shoppingCartID;
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getShoppingCartID() {
        return shoppingCartID;
    }
}
