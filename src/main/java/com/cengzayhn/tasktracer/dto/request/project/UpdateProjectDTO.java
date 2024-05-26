package com.cengzayhn.tasktracer.dto.request.project;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.ArrayList;

@Accessors(chain = true)
@Data
public class UpdateProjectDTO {

    private String id;

    private String name;

    private ArrayList<String> usernameList;
}
