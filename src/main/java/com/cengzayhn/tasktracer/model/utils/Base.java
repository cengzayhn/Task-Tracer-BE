package com.cengzayhn.tasktracer.model.utils;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Persistable;

import java.util.UUID;

@Accessors(chain = true)
public class Base implements Persistable<String> {

    @Id
    @Getter
    @Setter
    private String id = UUID.randomUUID().toString();

    @Override
    public boolean isNew() {
        return false;
    }
}
