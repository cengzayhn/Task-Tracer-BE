package com.cengzayhn.tasktracer.dto.request.user;

import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
public class AuthenticateDTO {

    private String username;

    private String password;
}
