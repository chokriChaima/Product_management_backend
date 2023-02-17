package com.softparadigm.ProductManagement.configurations;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Component
public class FirebaseMessagingObject {

    public FirebaseMessaging getFirebaseMessaging() {
        return firebaseMessaging;
    }

    final FirebaseMessaging firebaseMessaging;

    public FirebaseMessagingObject() throws IOException {
        GoogleCredentials googleCredentials = GoogleCredentials
                .fromStream(new ClassPathResource("firebase-adminsdk.json").getInputStream());
        FirebaseOptions firebaseOptions = FirebaseOptions
                .builder()
                .setCredentials(googleCredentials)
                .build();
        FirebaseApp app = FirebaseApp.initializeApp(firebaseOptions, "my-app");


        this.firebaseMessaging = FirebaseMessaging.getInstance(app);
    }

}