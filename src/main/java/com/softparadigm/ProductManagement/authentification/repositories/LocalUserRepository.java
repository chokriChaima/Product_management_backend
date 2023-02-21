package com.softparadigm.ProductManagement.authentification.repositories;

import com.softparadigm.ProductManagement.authentification.models.LocalUser;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LocalUserRepository extends MongoRepository<LocalUser,String> {
    LocalUser findUserByEmailAndPassword(String email, String password);
}
