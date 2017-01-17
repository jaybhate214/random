package com.att.cmlp.common.intentengine.main.java;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Greeting {

    private final String id;
    private final String content;
    
    @JsonCreator
    public Greeting(@JsonProperty("id") String id, @JsonProperty("content") String content) {
        this.id = id;
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
}
