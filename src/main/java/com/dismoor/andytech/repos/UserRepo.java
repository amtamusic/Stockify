package com.dismoor.andytech.repos;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.dismoor.andytech.models.User;

@Repository
public interface UserRepo extends MongoRepository<User, String> {

}
