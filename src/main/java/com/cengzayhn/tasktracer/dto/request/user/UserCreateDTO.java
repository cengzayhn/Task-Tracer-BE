package com.cengzayhn.tasktracer.dto.request.user;


import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
public class UserCreateDTO {

    private String name;

    private String surname;

    private String username;

    private String password;
}
