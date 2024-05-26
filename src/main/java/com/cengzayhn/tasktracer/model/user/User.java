package com.cengzayhn.tasktracer.model.user;

import com.cengzayhn.tasktracer.model.utils.Base;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.data.mongodb.core.mapping.Document;

@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Data
@Document(collection = "user")
public class User extends Base {

    private String name;

    private String surname;

    private String username;

    private String password;

    private String[] projectIdList;
}