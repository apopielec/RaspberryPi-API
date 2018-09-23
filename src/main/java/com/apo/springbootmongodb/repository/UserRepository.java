package com.apo.springbootmongodb.repository;

import com.apo.springbootmongodb.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends MongoRepository<User, String>{

    @Query(value = "{ 'date.birthMonth' : { $gt: ?0 } }")
    List<User> findByBirthdayMonthGreaterThan(int month);

    @Query(value = "{ 'date.nameMonth' : { $gt: ?0 } }")
    List<User> findByNamedayMonthGreaterThan(int month);
}
