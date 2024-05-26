package com.cengzayhn.tasktracer.controller.user;

import com.cengzayhn.tasktracer.dto.request.user.AuthenticateDTO;
import com.cengzayhn.tasktracer.dto.request.user.UserCreateDTO;
import com.cengzayhn.tasktracer.model.user.User;
import com.cengzayhn.tasktracer.service.project.TaskTracerProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("task-tracer/user/")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    TaskTracerProjectService taskTracerProjectService;

    @PostMapping("create")
    public ResponseEntity<User> create(@RequestBody UserCreateDTO userCreateDTO){
        User user = taskTracerProjectService.create(userCreateDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }
    @PostMapping("authenticate")
    public ResponseEntity<User> authenticateUser(@RequestBody AuthenticateDTO authenticateDTO) {
        User user = taskTracerProjectService.authenticateUser(authenticateDTO);
        return ResponseEntity.ok(user);
    }

    @Autowired
    public void setTaskTracerProjectService(TaskTracerProjectService taskTracerProjectService) {
        this.taskTracerProjectService = taskTracerProjectService;
    }
}
