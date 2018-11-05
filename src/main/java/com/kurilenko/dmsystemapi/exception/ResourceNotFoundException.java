package com.kurilenko.dmsystemapi.exception;

import lombok.NoArgsConstructor;

import java.util.Map;

@NoArgsConstructor
public abstract class ResourceNotFoundException extends Exception {
    ResourceNotFoundException(String message) {
        super(message);
    }

    public abstract Map<String, String> getMessageParams();
}
