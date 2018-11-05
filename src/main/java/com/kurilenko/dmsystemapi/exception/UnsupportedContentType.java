package com.kurilenko.dmsystemapi.exception;

import lombok.Getter;

public class UnsupportedContentType extends Exception {
    @Getter
    private final String unsupportedContentTypeName;

    public UnsupportedContentType(String name) {
        super(String.format("Unsupported content type found: %s", name));
        this.unsupportedContentTypeName = name;
    }
}
