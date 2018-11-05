package com.kurilenko.dmsystemapi.exception;

import lombok.Getter;

import java.io.IOException;

public class StreamReaderException extends IOException {
    @Getter
    private final String fileName;

    public StreamReaderException(String message){
        super(String.format("Can't read file:  ", message));
        this.fileName = message;
    }
}
