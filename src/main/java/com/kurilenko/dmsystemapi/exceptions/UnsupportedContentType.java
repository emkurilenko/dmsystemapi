package com.kurilenko.dmsystemapi.exceptions;

import lombok.Getter;

public class UnsupportedContentType extends Exception {
    @Getter
    private final String unsupportedContentTypeName;

    public UnsupportedContentType(String name){
        super(String.format("unsupported content type found: %s", name));
        this.unsupportedContentTypeName = name;
    }
}
