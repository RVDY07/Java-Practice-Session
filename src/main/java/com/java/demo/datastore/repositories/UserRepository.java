package com.java.demo.datastore.repositories;

import com.java.demo.datastore.model.UserModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<UserModel,String> {

    Optional<UserModel> findByUserName(String userName);

}
