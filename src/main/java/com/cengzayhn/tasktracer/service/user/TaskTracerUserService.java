package com.cengzayhn.tasktracer.service.user;

import com.cengzayhn.tasktracer.model.user.User;
import com.cengzayhn.tasktracer.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskTracerUserService {

    UserRepository userRepository;

    public List<User> getAll(){
        return userRepository.findAll();
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
