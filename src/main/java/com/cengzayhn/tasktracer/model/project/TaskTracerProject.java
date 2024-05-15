package com.cengzayhn.tasktracer.model.project;

import com.cengzayhn.tasktracer.model.utils.Base;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

@EqualsAndHashCode(callSuper = true)
@Data
@Document(collection = "project")
@Accessors(chain = true)
public class TaskTracerProject extends Base {

    private String name;

    private String createdDate;

    private String closedDate;

    private ArrayList<String> taskIdList;

    private ArrayList<String> memberIdList;

    private Boolean isOpen = true;
//her task bir projeye ait olmalı , her member ise birden fazla projeye ait olabilir
}
