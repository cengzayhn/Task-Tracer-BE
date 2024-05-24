package com.cengzayhn.tasktracer.service.task;

import com.cengzayhn.tasktracer.dto.request.task.TaskCreateDTO;
import com.cengzayhn.tasktracer.dto.request.task.TaskUpdateDTO;
import com.cengzayhn.tasktracer.model.task.State;
import com.cengzayhn.tasktracer.model.task.TaskTracerTask;
import com.cengzayhn.tasktracer.repository.task.TaskTracerTaskRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@Service
public class TaskTracerTaskService {


    ModelMapper modelMapper;
    TaskTracerTaskRepository taskTracerTaskRepository;

    public TaskTracerTask createTask(TaskCreateDTO taskCreateDTO){
        TaskTracerTask taskTracerTask = modelMapper.map(taskCreateDTO, TaskTracerTask.class);
        taskTracerTask.setId(UUID.randomUUID().toString());
        taskTracerTask.setTitle(taskCreateDTO.getTitle());
        taskTracerTask.setDescription(taskCreateDTO.getDescription());
        taskTracerTask.setCreatedBy(taskCreateDTO.getCreatedBy());
        taskTracerTask.setCreatedDate(taskCreateDTO.getCreatedDate());
        taskTracerTask.setState(State.OPEN);
        return taskTracerTaskRepository.save(taskTracerTask);
    }

    public TaskTracerTask getById(String taskId) {
        return taskTracerTaskRepository.findById(taskId)
                .orElseThrow(() -> new NoSuchElementException("Task not found with id: " + taskId));
    }

    public TaskTracerTask update(TaskUpdateDTO taskUpdateDTO){
        TaskTracerTask taskTracerTask = getById(taskUpdateDTO.getId());
        taskTracerTask.setTitle(taskUpdateDTO.getTitle());
        taskTracerTask.setDescription(taskUpdateDTO.getDescription());
        taskTracerTask.setCreatedBy(taskUpdateDTO.getCreatedBy());
        taskTracerTask.setState(taskUpdateDTO.getState());
        taskTracerTaskRepository.save(taskTracerTask);
        return taskTracerTask;
    }

    public List<TaskTracerTask> getTasksByDateAndProject(String createdDate, String projectId){
        return taskTracerTaskRepository.findAllByCreatedDateAndProjectId(createdDate, projectId);
    }


    @Autowired
    public void setTaskTracerTaskRepository(TaskTracerTaskRepository taskTracerTaskRepository) {
        this.taskTracerTaskRepository = taskTracerTaskRepository;
    }

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
}
