package com.cengzayhn.tasktracer.service.project;

import com.cengzayhn.tasktracer.dto.request.project.ProjectCreateDTO;
import com.cengzayhn.tasktracer.model.project.TaskTracerProject;
import com.cengzayhn.tasktracer.repository.project.TaskTracerProjectRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.NoSuchElementException;

@Service
public class TaskTracerProjectService {

    ModelMapper modelMapper;
    TaskTracerProjectRepository taskTracerProjectRepository;

    public TaskTracerProject createProject(ProjectCreateDTO projectCreateDTO){
        TaskTracerProject taskTracerProject = new TaskTracerProject();
        taskTracerProject.setName(projectCreateDTO.getName());
        taskTracerProject.setTaskIdList(projectCreateDTO.getTaskIdList());
        taskTracerProject.setMemberIdList(projectCreateDTO.getMemberIdList());
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

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Autowired
    public void setTaskTracerProjectRepository(TaskTracerProjectRepository taskTracerProjectRepository) {
        this.taskTracerProjectRepository = taskTracerProjectRepository;
    }
}
