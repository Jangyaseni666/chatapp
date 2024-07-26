package com.jangyaseni.chatapp.user;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserRepo extends MongoRepository<User, String> {
    List<User> findAllByStatus(Status status);
}
