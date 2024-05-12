package com.cengzayhn.tasktracer.dto.request.project;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.ArrayList;

@Accessors(chain = true)
@Data
public class ProjectCreateDTO {

    private String name;

    private ArrayList<String> taskIdList;

    private ArrayList<String> memberIdList;

}
