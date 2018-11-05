package com.kurilenko.dmsystemapi.exception;

import lombok.Getter;

import java.util.Collections;
import java.util.Map;

public class TagNotFoundException extends ResourceNotFoundException {

    private final String tag;

    public TagNotFoundException(String tag) {
        super(String.format("Tag with ID = %s has not been found", tag));
        this.tag = tag;
    }

    @Override
    public Map<String, String> getMessageParams() {
        return Collections.singletonMap("tag", tag);
    }
}
