package com.cengzayhn.tasktracer.repository.project;

import com.cengzayhn.tasktracer.model.project.TaskTracerProject;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TaskTracerProjectRepository extends MongoRepository<TaskTracerProject, String> {
}
