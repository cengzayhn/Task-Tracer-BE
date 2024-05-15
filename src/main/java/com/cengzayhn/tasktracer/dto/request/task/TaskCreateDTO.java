package com.cengzayhn.tasktracer.dto.request.task;

import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
public class TaskCreateDTO {

    private String projectId;

    private String title;

    private String description;

    private String createdBy;

    private String createdDate;
}
