package com.kurilenko.dmsystemapi.exception;

import java.util.Collections;
import java.util.Map;

public class DocumentNotFoundException extends ResourceNotFoundException {
    private final String doc;

    public DocumentNotFoundException(String doc) {
        super(String.format("Document with ID or File Name = %s has not been found", doc));
        this.doc = doc;
    }

    @Override
    public Map<String, String> getMessageParams() {
        return Collections.singletonMap("doc", doc);
    }
}
