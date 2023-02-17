package com.softparadigm.ProductManagement.notification;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "payment-notifications")
public class PaymentNotification {
    @Id
    private String id;
    private String shoppingCartID ;
    private String body;
    private String title;

    public PaymentNotification( String title,String body,String shoppingCartID) {
        this.body = body;
        this.title = title;
        this.shoppingCartID = shoppingCartID ;
    }

    public String getShoppingCartID() {
        return shoppingCartID;
    }

    public void setShoppingCartID(String shoppingCartID) {
        this.shoppingCartID = shoppingCartID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
