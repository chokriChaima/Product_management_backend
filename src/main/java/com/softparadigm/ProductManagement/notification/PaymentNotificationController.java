package com.softparadigm.ProductManagement.notification;

import com.softparadigm.ProductManagement.payment.PaymentService;
import com.softparadigm.ProductManagement.security.UserDetailsCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/secure/payment-notifications")
public class PaymentNotificationController {

    private final PaymentNotificationService paymentNotificationService;

    @Autowired
    public PaymentNotificationController(PaymentNotificationService paymentNotificationService) {
        this.paymentNotificationService = paymentNotificationService;
    }

    @GetMapping
    List<PaymentNotification> getAll() {
        return paymentNotificationService.getAll();
    }

    @GetMapping("for-cart")
    List<PaymentNotification> getAllByShoppingCartID() {
        UserDetailsCustom user = (UserDetailsCustom) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return paymentNotificationService.getAllByShoppingCartID(user.getShoppingCartID());
    }




}
