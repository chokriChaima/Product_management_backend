package com.softparadigm.ProductManagement.payment;


import com.softparadigm.ProductManagement.notification.PaymentNotificationService;
import com.softparadigm.ProductManagement.shoppingcart.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentNotificationService paymentNotificationService;
    private final ShoppingCartService shoppingCartService;

    @Autowired
    public PaymentService(ShoppingCartService shoppingCartService,PaymentRepository paymentRepository, PaymentNotificationService paymentNotificationService) {
        this.paymentRepository = paymentRepository;
        this.paymentNotificationService = paymentNotificationService;
        this.shoppingCartService = shoppingCartService;
    }

    public List<Payment> getPayments() {
        return paymentRepository.findAll();
    }

    public Payment addPayment(Payment newPayment) {


        Payment payment = paymentRepository.save(newPayment);

        shoppingCartService.closeShoppingCart(payment.getShoppingCartID());

        System.out.println("------------------------------------------" +
                paymentNotificationService.createNotification(payment)+"------------------------------------------" );


        return  payment;

    }


    public Payment getPaymentByID(String PaymentId) {
        return paymentRepository.findById(PaymentId).get();
    }
}
