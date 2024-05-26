package com.cengzayhn.tasktracer.repository.user;

import com.cengzayhn.tasktracer.model.user.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByUsername(String username);
}
