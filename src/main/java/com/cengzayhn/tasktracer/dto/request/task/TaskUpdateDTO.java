package com.cengzayhn.tasktracer.dto.request.task;

import com.cengzayhn.tasktracer.model.task.State;
import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
public class TaskUpdateDTO {

    private String id;

    private String title;

    private String description;

    private String createdBy;

    private State state;
}
