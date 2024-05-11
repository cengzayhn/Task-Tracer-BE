package com.cengzayhn.tasktracer.repository.task;

import com.cengzayhn.tasktracer.model.task.TaskTracerTask;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TaskTracerTaskRepository extends MongoRepository<TaskTracerTask, String> {

}
