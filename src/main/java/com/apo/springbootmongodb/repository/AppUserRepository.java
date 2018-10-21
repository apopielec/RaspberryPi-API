package com.apo.springbootmongodb.repository;

import com.apo.springbootmongodb.model.AppUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUserRepository extends MongoRepository<AppUser, String>{

    AppUser findByUsername(String username);
}
