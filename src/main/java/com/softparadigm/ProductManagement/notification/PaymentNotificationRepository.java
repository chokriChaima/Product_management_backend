package com.softparadigm.ProductManagement.notification;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PaymentNotificationRepository extends MongoRepository<PaymentNotification,String> {
    List<PaymentNotification> findAllByShoppingCartID(String shoppingCartID) ;
}
