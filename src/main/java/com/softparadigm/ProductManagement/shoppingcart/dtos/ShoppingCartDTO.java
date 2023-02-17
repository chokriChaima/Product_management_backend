package com.softparadigm.ProductManagement.shoppingcart.dtos;

import java.util.List;
import java.util.Objects;

public class ShoppingCartDTO {

    private String id;
    private List<ProductInfoDTO> productInfoDTOList;
    double totalPrice ;
    int totalQuantity ;
    private boolean paid;

    public ShoppingCartDTO(String id, List<ProductInfoDTO> productInfoDTOList, double totalPrice, int totalQuantity, boolean paid) {
        this.id = id;
        this.productInfoDTOList = productInfoDTOList;
        this.totalPrice = totalPrice;
        this.totalQuantity = totalQuantity;
        this.paid = paid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShoppingCartDTO that = (ShoppingCartDTO) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "ShoppingCartDTO{" +
                "id='" + id + '\'' +
                ", productInfoDTOList=" + productInfoDTOList +
                ", totalPrice=" + totalPrice +
                ", totalQuantity=" + totalQuantity +
                ", paid=" + paid +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<ProductInfoDTO> getproductInfoDTOList() {
        return productInfoDTOList;
    }

    public void setproductInfoDTOList(List<ProductInfoDTO> productInfoDTOList) {
        productInfoDTOList = productInfoDTOList;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(int totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public boolean getPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }
}
