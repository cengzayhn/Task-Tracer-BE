package com.cengzayhn.tasktracer.repository.task;

import com.cengzayhn.tasktracer.model.task.TaskTracerTask;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TaskTracerTaskRepository extends MongoRepository<TaskTracerTask, String> {
    List<TaskTracerTask> findAllByCreatedDateAndProjectId(String createdDate, String projectId);

}
