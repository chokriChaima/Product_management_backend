package com.softparadigm.ProductManagement.users.repositories;

import com.softparadigm.ProductManagement.users.entities.LocalUser;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LocalUserRepository extends MongoRepository<LocalUser,String> {

    LocalUser findUserByPassword(String password);
    LocalUser findUserByEmail(String username);
}
