package com.softparadigm.ProductManagement.authentification.models;


import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Document(collection = "facebook-users")
public class FacebookUser {

    private String id;
    private String name;
    private String email;
    private String shoppingCartID;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FacebookUser that = (FacebookUser) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(email, that.email) && Objects.equals(shoppingCartID, that.shoppingCartID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, shoppingCartID);
    }

    @Override
    public String toString() {
        return "FacebookUser{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", shoppingCartID='" + shoppingCartID + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getShoppingCartID() {
        return shoppingCartID;
    }

    public FacebookUser() {
    }

    public void setShoppingCartID(String shoppingCartID) {
        this.shoppingCartID = shoppingCartID;
    }
}
