package com.softparadigm.ProductManagement.shoppingcart.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Document(collection = "shopping-carts")
public class ShoppingCart {
    @Id
    private String id;

    private boolean paid;
    @DBRef
    private List<ProductInfo> ProductInfoList;

    @Override
    public String toString() {
        return "ShoppingCart{" +
                "id='" + id + '\'' +
                ", paid=" + paid +
                ", ProductInfoList=" + ProductInfoList +
                '}';
    }

    public ShoppingCart() {
        this.ProductInfoList = new ArrayList<ProductInfo>();
        this.paid = false;

    }

    public boolean getPaid() {
        return this.paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }



    public void setProductInfoList(List<ProductInfo> ProductInfoList) {
        this.ProductInfoList = ProductInfoList;
    }

    public List<ProductInfo> getProductInfoList() {
        return ProductInfoList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShoppingCart that = (ShoppingCart) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isPaid() {
        return paid;
    }
}
