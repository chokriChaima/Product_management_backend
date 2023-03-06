package com.softparadigm.ProductManagement.users.repositories;

import com.softparadigm.ProductManagement.users.entities.FacebookUser;
import com.softparadigm.ProductManagement.users.entities.LocalUser;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FacebookUserRepository extends MongoRepository<FacebookUser,String> {
    FacebookUser findUserByFacebookId(String facebookId);
    FacebookUser findUserByEmail(String email);
}


