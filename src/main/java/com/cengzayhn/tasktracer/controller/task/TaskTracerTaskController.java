package com.cengzayhn.tasktracer.controller.task;

import com.cengzayhn.tasktracer.dto.request.task.TaskCreateDTO;
import com.cengzayhn.tasktracer.dto.request.task.TaskUpdateDTO;
import com.cengzayhn.tasktracer.model.task.TaskTracerTask;
import com.cengzayhn.tasktracer.service.task.TaskTracerTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("task-tracer/task/")
@CrossOrigin(origins = "http://localhost:3000")
public class TaskTracerTaskController {

    TaskTracerTaskService taskTracerTaskService;

    @PostMapping("create")
    public ResponseEntity<TaskTracerTask> createTask(@RequestBody TaskCreateDTO taskCreateDTO){
        TaskTracerTask taskTracerTask = taskTracerTaskService.createTask(taskCreateDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(taskTracerTask);
    }

    @PutMapping("update")
    public ResponseEntity<TaskTracerTask> updateTask(@RequestBody TaskUpdateDTO taskUpdateDTO){
        TaskTracerTask taskTracerTask = taskTracerTaskService.update(taskUpdateDTO);
        return ResponseEntity.status(HttpStatus.OK).body(taskTracerTask);
    }

    @GetMapping("{projectId}/by-projectId")
    public ResponseEntity<List<TaskTracerTask>> getByProjectId(@PathVariable String projectId){
        List<TaskTracerTask> taskTracerTaskList = taskTracerTaskService.getByProjectId(projectId);
        return ResponseEntity.status(HttpStatus.OK).body(taskTracerTaskList);
    }


    @Autowired
    public void setTaskTracerTaskService(TaskTracerTaskService taskTracerTaskService) {
        this.taskTracerTaskService = taskTracerTaskService;
    }
}
