package com.softparadigm.ProductManagement.notification;

import com.softparadigm.ProductManagement.payment.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/payment-notifications")
public class PaymentNotificationController {

    private final PaymentNotificationService paymentNotificationService ;

    @Autowired
    public PaymentNotificationController(PaymentNotificationService paymentNotificationService) {
        this.paymentNotificationService = paymentNotificationService;
    }

    @GetMapping
    List<PaymentNotification> getAll() {
        return paymentNotificationService.getAll();
    }

    @GetMapping("/shopping-cart/{id}")
    List<PaymentNotification> getAllByShoppingCartID(@PathVariable String id) {
        return paymentNotificationService.getAllByShoppingCartID(id);
    }

    @GetMapping("/{id}")
    PaymentNotification getAll(@PathVariable String id) {
        return paymentNotificationService.getNotificationByID(id);
    }


}
