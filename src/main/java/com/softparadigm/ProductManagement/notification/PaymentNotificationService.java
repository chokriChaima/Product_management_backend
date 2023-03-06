package com.softparadigm.ProductManagement.notification;

import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import com.softparadigm.ProductManagement.configurations.FirebaseMessagingObject;
import com.softparadigm.ProductManagement.payment.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentNotificationService {
    private PaymentNotificationRepository paymentNotificationRepository;
    private final FirebaseMessagingObject firebaseMessagingObject;
    private static final String _TOKEN = "cIVG_xJDSby5kABkt-A4n8:APA91bFk3JytZJlNnahS3YFN_oDQdbE1inPFYvjiEAI5PL4O2mZw2-mFx_iDID2zTj7RGGp2T5qAaBVLWPJMwaMyms_ZeAFL6fRAU9WpIIMabmWq7pVdCXNGVRrqnxHTiv185wCjkxYm";


    @Autowired
    public PaymentNotificationService(PaymentNotificationRepository paymentNotificationRepository, FirebaseMessagingObject firebaseMessaging) {
        this.paymentNotificationRepository = paymentNotificationRepository;
        this.firebaseMessagingObject = firebaseMessaging;
    }

    public String sendNotification(PaymentNotification paymentNotification) throws FirebaseMessagingException {

        Notification notification = Notification.builder()
                .setTitle(paymentNotification.getTitle())
                .setBody(paymentNotification.getBody())
                .build();

        System.out.println("inside sending notification " + notification.toString());
        Message message = Message
                .builder()
                .setToken(_TOKEN)
                .setNotification(notification)
                .build();

        System.out.println("ready to send message " + message.toString());
        return firebaseMessagingObject.getFirebaseMessaging().send(message);

    }

    public String createNotification(Payment payment) {

        PaymentNotification paymentNotification = new PaymentNotification(
                "Payment Notification For " + payment.getCardHolderName(),
                payment.getCardHolderName() + ", your Payment of " + payment.getPaymentAmount() + " has been successful",
                payment.getShoppingCartID()
        );

        try {
            return sendNotification(paymentNotificationRepository.save(paymentNotification));
        } catch (FirebaseMessagingException e) {
            e.printStackTrace();
            return e.toString();
        }
    }

    public List<PaymentNotification> getAll() {
        return paymentNotificationRepository.findAll();
    }

    public List<PaymentNotification> getAllByShoppingCartID(String id) {
        return paymentNotificationRepository.findAllByShoppingCartID(id);
    }


}
