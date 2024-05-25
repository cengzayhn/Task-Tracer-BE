package com.cengzayhn.tasktracer.controller.project;

import com.cengzayhn.tasktracer.dto.request.project.ProjectCreateDTO;
import com.cengzayhn.tasktracer.model.project.TaskTracerProject;
import com.cengzayhn.tasktracer.service.project.TaskTracerProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("task-tracer/project/")
@CrossOrigin(origins = "http://localhost:3000")
public class TaskTracerProjectController {

    TaskTracerProjectService taskTracerProjectService;

    @PostMapping("create")
    public ResponseEntity<TaskTracerProject> createProject(@RequestBody ProjectCreateDTO projectCreateDTO){
        TaskTracerProject taskTracerProject = taskTracerProjectService.createProject(projectCreateDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(taskTracerProject);
    }



    @Autowired
    public void setTaskTracerProjectService(TaskTracerProjectService taskTracerProjectService) {
        this.taskTracerProjectService = taskTracerProjectService;
    }
}
