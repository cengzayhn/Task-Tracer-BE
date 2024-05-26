package com.cengzayhn.tasktracer.model.task;

import lombok.experimental.Accessors;

@Accessors(chain = true)
public enum State {
    TODO("TODO"), IN_PROGRESS("IN_PROGRESS"), IN_TEST("IN_TEST"), DONE("DONE");

    private final String text;

    State(final String text){
        this.text = text;
    }

    @Override
    public String toString(){
        return text;
    }
}
