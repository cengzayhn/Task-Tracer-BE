package com.cengzayhn.tasktracer.service.project;

import com.cengzayhn.tasktracer.dto.request.project.ProjectCreateDTO;
import com.cengzayhn.tasktracer.dto.request.project.UpdateProjectDTO;
import com.cengzayhn.tasktracer.dto.request.user.AuthenticateDTO;
import com.cengzayhn.tasktracer.dto.request.user.UserCreateDTO;
import com.cengzayhn.tasktracer.model.project.TaskTracerProject;
import com.cengzayhn.tasktracer.model.user.User;
import com.cengzayhn.tasktracer.repository.project.TaskTracerProjectRepository;
import com.cengzayhn.tasktracer.repository.user.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class TaskTracerProjectService {

    ModelMapper modelMapper;
    TaskTracerProjectRepository taskTracerProjectRepository;
    UserRepository userRepository;

    public TaskTracerProject createProject(ProjectCreateDTO projectCreateDTO){
        TaskTracerProject taskTracerProject = new TaskTracerProject();
        taskTracerProject.setName(projectCreateDTO.getName());
        taskTracerProject.setTaskIdList(projectCreateDTO.getTaskIdList());
        taskTracerProject.setUsernameList(projectCreateDTO.getUsernameList());
        taskTracerProject.setCreatedDate(LocalDateTime.now().toString());
        taskTracerProject.setIsOpen(true);
        return taskTracerProjectRepository.save(taskTracerProject);
    }

    public TaskTracerProject addTask(String projectId, String taskId){
        TaskTracerProject taskTracerProject = getById(projectId);
        if (taskTracerProject.getTaskIdList() == null){
            taskTracerProject.setTaskIdList(new ArrayList<>());
        }
        taskTracerProject.getTaskIdList().add(taskId);
        taskTracerProjectRepository.save(taskTracerProject);
        return taskTracerProject;
    }

    public TaskTracerProject getById(String projectId) {
        return taskTracerProjectRepository.findById(projectId)
                .orElseThrow(() -> new NoSuchElementException("Task not found with id: " + projectId));
    }

    public User create(UserCreateDTO userCreateDTO){
        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setName(userCreateDTO.getName());
        user.setSurname(userCreateDTO.getSurname());
        user.setUsername(userCreateDTO.getUsername());
        user.setPassword(userCreateDTO.getPassword());
        TaskTracerProject taskTracerProject = getById("00c62643-603f-4b64-8b59-2eec37d48815");
        taskTracerProject.getUsernameList().add(userCreateDTO.getUsername());
        taskTracerProjectRepository.save(taskTracerProject);
        userRepository.save(user);
        return user;
    }

    public User authenticateUser(AuthenticateDTO authenticateDTO) {
        Optional<User> userOptional = userRepository.findByUsername(authenticateDTO.getUsername());
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (authenticateDTO.getPassword().equals(user.getPassword())) {
                return user;
            }
        }
        throw new RuntimeException("Invalid username or password");
    }

    public List<TaskTracerProject> findProjectsByCreator(String username){
        return taskTracerProjectRepository.findByUsernameListContainingAndIsOpen(username, true);
    }

    public TaskTracerProject updateProject(UpdateProjectDTO updateProjectDTO){
        TaskTracerProject project = getById(updateProjectDTO.getId());
        project.setName(updateProjectDTO.getName());
        project.setUsernameList(updateProjectDTO.getUsernameList());
        taskTracerProjectRepository.save(project);
        return project;
    }

    public TaskTracerProject closeProject(String projectId){
        TaskTracerProject taskTracerProject = getById(projectId);
        taskTracerProject.setIsOpen(false);
        taskTracerProject.setClosedDate(LocalDateTime.now().toString());
        taskTracerProjectRepository.save(taskTracerProject);
        return taskTracerProject;
    }

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Autowired
    public void setTaskTracerProjectRepository(TaskTracerProjectRepository taskTracerProjectRepository) {
        this.taskTracerProjectRepository = taskTracerProjectRepository;
    }
    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
