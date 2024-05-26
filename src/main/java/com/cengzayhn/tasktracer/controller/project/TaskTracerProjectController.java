package com.cengzayhn.tasktracer.controller.project;

import com.cengzayhn.tasktracer.dto.request.project.ProjectCreateDTO;
import com.cengzayhn.tasktracer.dto.request.project.UpdateProjectDTO;
import com.cengzayhn.tasktracer.model.project.TaskTracerProject;
import com.cengzayhn.tasktracer.service.project.TaskTracerProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("{username}/by-username")
    public ResponseEntity<List<TaskTracerProject>> findByCreator(@PathVariable String username){
        List<TaskTracerProject> taskTracerProjectList = taskTracerProjectService.findProjectsByCreator(username);
        return ResponseEntity.status(HttpStatus.OK).body(taskTracerProjectList);
    }
    @PutMapping("update")
    public ResponseEntity<TaskTracerProject> update(@RequestBody UpdateProjectDTO updateProjectDTO){
        TaskTracerProject taskTracerProject = taskTracerProjectService.updateProject(updateProjectDTO);
        return ResponseEntity.status(HttpStatus.OK).body(taskTracerProject);
    }
    @GetMapping("{projectId}/closed")
    public ResponseEntity<TaskTracerProject> close(@PathVariable String projectId){
        TaskTracerProject taskTracerProject = taskTracerProjectService.closeProject(projectId);
        return ResponseEntity.status(HttpStatus.OK).body(taskTracerProject);
    }


    @Autowired
    public void setTaskTracerProjectService(TaskTracerProjectService taskTracerProjectService) {
        this.taskTracerProjectService = taskTracerProjectService;
    }
}
