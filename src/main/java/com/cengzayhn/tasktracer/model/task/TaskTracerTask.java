package com.cengzayhn.tasktracer.model.task;

import com.cengzayhn.tasktracer.model.utils.Base;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.data.mongodb.core.mapping.Document;

@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Data
@Document
public class TaskTracerTask extends Base {

    private String title;

    private String description;

    private String createdBy;

    private String createdDate;
}
