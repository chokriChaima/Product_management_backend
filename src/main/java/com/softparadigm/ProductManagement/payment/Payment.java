package com.softparadigm.ProductManagement.payment;

import com.softparadigm.ProductManagement.shoppingcart.entities.ShoppingCart;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Document(collection = "payments")
public class Payment {
    @Id
   private String paymentID ;
    private String  shoppingCartID ;
    private double paymentAmount;
    private String cardNumber;
    private String cardHolderName ;

    @Override
    public String toString() {
        return "Payment{" +
                "paymentID='" + paymentID + '\'' +
                ", shoppingCartID='" + shoppingCartID + '\'' +
                ", paymentAmount=" + paymentAmount +
                ", cardNumber='" + cardNumber + '\'' +
                ", cardHolderName='" + cardHolderName + '\'' +
                '}';
    }

    public Payment(String shoppingCartID, double paymentAmount, String cardNumber, String cardHolderName) {
        this.shoppingCartID = shoppingCartID;
        this.paymentAmount = paymentAmount;
        this.cardNumber = cardNumber;
        this.cardHolderName = cardHolderName;
    }

    public String getShoppingCartID() {
        return shoppingCartID;
    }

    public void setShoppingCartID(String shoppingCartID) {
        this.shoppingCartID = shoppingCartID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment = (Payment) o;
        return Double.compare(payment.paymentAmount, paymentAmount) == 0 && Objects.equals(paymentID, payment.paymentID) && Objects.equals(cardNumber, payment.cardNumber) && Objects.equals(cardHolderName, payment.cardHolderName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(paymentID, paymentAmount, cardNumber, cardHolderName);
    }

    public String getPaymentID() {
        return paymentID;
    }

    public void setPaymentID(String paymentID) {
        this.paymentID = paymentID;
    }

    public double getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(double paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardHolderName() {
        return cardHolderName;
    }

    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }
}
