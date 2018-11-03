package com.kurilenko.dmsystemapi.exception;

import java.util.Collections;
import java.util.Map;

public class DocumentNotFoundException extends ResourceNotFoundException {
    private final String docId;

    public DocumentNotFoundException(String docId) {
        super(String.format("Document with ID = %s has not been found", docId));
        this.docId = docId;
    }

    @Override
    public Map<String, String> getMessageParams() {
        return Collections.singletonMap("docId", docId);
    }
}
