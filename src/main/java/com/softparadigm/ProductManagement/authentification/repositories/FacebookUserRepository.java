package com.softparadigm.ProductManagement.authentification.repositories;

import com.softparadigm.ProductManagement.authentification.models.FacebookUser;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FacebookUserRepository extends MongoRepository<FacebookUser,String> {

}
