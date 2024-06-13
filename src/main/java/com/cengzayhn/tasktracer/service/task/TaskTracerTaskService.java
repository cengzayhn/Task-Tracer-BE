package com.cengzayhn.tasktracer.service.task;

import com.cengzayhn.tasktracer.dto.request.task.TaskCreateDTO;
import com.cengzayhn.tasktracer.dto.request.task.TaskUpdateDTO;
import com.cengzayhn.tasktracer.model.task.State;
import com.cengzayhn.tasktracer.model.task.TaskTracerTask;
import com.cengzayhn.tasktracer.repository.task.TaskTracerTaskRepository;
import com.cengzayhn.tasktracer.service.project.TaskTracerProjectService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class TaskTracerTaskService {


    ModelMapper modelMapper;
    TaskTracerTaskRepository taskTracerTaskRepository;
    TaskTracerProjectService taskTracerProjectService;

    public TaskTracerTask createTask(TaskCreateDTO taskCreateDTO){
        TaskTracerTask taskTracerTask = modelMapper.map(taskCreateDTO, TaskTracerTask.class);
        taskTracerTask.setId(UUID.randomUUID().toString());
        taskTracerTask.setProjectId(taskCreateDTO.getProjectId());
        taskTracerTask.setTitle(taskCreateDTO.getTitle());
        taskTracerTask.setDescription(taskCreateDTO.getDescription());
        taskTracerTask.setCreatedBy(taskCreateDTO.getCreatedBy());
        taskTracerTask.setCreatedDate(taskCreateDTO.getCreatedDate());
        taskTracerTask.setState(State.TODO);
        taskTracerProjectService.addTask(taskCreateDTO.getProjectId(), taskTracerTask.getId());
        return taskTracerTaskRepository.save(taskTracerTask);
    }

    public TaskTracerTask getById(String taskId) {
        return taskTracerTaskRepository.findById(taskId)
                .orElseThrow(() -> new NoSuchElementException("Task not found with id: " + taskId));
    }

    public TaskTracerTask update(TaskUpdateDTO taskUpdateDTO){
        TaskTracerTask taskTracerTask = getById(taskUpdateDTO.getId());
        if (taskUpdateDTO.getTitle() != null){
            taskTracerTask.setTitle(taskUpdateDTO.getTitle());
        }
        if (taskUpdateDTO.getDescription() != null){
            taskTracerTask.setDescription(taskUpdateDTO.getDescription());
        }
        if (taskUpdateDTO.getCreatedBy() != null){
            taskTracerTask.setCreatedBy(taskUpdateDTO.getCreatedBy());
        }
        if (taskUpdateDTO.getState() != null){
            taskTracerTask.setState(taskUpdateDTO.getState());
        }
        taskTracerTaskRepository.save(taskTracerTask);
        return taskTracerTask;
    }

    public TaskTracerTask delete(String id){
        TaskTracerTask task = getById(id);
        taskTracerTaskRepository.delete(task);
        return task;
    }

    public List<TaskTracerTask> getByProjectId(String projectId){
        return taskTracerTaskRepository.findAllByProjectId(projectId);
    }

    public List<TaskTracerTask> getTasksByDateAndProject(String createdDate, String projectId){
        return taskTracerTaskRepository.findAllByCreatedDateAndProjectId(createdDate, projectId);
    }


    @Autowired
    public void setTaskTracerTaskRepository(TaskTracerTaskRepository taskTracerTaskRepository) {
        this.taskTracerTaskRepository = taskTracerTaskRepository;
    }

    @Autowired
    public void setTaskTracerProjectService(TaskTracerProjectService taskTracerProjectService) {
        this.taskTracerProjectService = taskTracerProjectService;
    }

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
}
