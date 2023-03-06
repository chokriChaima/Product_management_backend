package com.softparadigm.ProductManagement.payment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("api/secure/payment")
public class PaymentController {

    final PaymentService _paymentService ;

    @Autowired
    PaymentController(PaymentService paymentService){
        this._paymentService = paymentService;
    };


    @PostMapping("/addPayment")
    Payment addPayment(@RequestBody Payment newPayment)
    {
        return this._paymentService.addPayment(newPayment);
    }

    @GetMapping("/{id}")
    Payment getPaymentByID(@PathVariable  String id){
        return this._paymentService.getPaymentByID(id);
    }
}
