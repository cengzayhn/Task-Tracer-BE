package com.cengzayhn.tasktracer.repository.project;

import com.cengzayhn.tasktracer.model.project.TaskTracerProject;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TaskTracerProjectRepository extends MongoRepository<TaskTracerProject, String> {
    List<TaskTracerProject> findByUsernameListContainingAndIsOpen(String username, boolean isOpen);
}
