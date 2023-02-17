package com.softparadigm.ProductManagement.shoppingcart.dtos;

import com.softparadigm.ProductManagement.product.Product;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.Objects;

public class ProductInfoDTO {

    private String productID;
    private String productInfoID ;
    //Product Information
    private String productName;
    private double productPrice;

    //Extra Info
    private int quantity;
    private double totalPrice;

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductInfoDTO that = (ProductInfoDTO) o;
        return productID.equals(that.productID);
    }

    public String getProductInfoID() {
        return productInfoID;
    }

    public void setProductInfoID(String productInfoID) {
        this.productInfoID = productInfoID;
    }

    @Override
    public int hashCode() {
        return Objects.hash(productID);
    }

    @Override
    public String toString() {
        return "ProductInfoDTO{" +
                "productID='" + productID + '\'' +
                ", productInfoID='" + productInfoID + '\'' +
                ", productName='" + productName + '\'' +
                ", productPrice=" + productPrice +
                ", quantity=" + quantity +
                ", totalPrice=" + totalPrice +
                '}';
    }

    public ProductInfoDTO() {
        this.quantity = 1;
        this.totalPrice = this.productPrice;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
