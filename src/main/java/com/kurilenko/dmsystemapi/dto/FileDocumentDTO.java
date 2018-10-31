package com.kurilenko.dmsystemapi.dto;

import lombok.NonNull;
import lombok.Value;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Value
public class FileDocumentDTO {
    @NonNull
    Long id;
    @NonNull
    String name;

    @NonNull
    String contentType, extension;

    @NonNull
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    Date creationDate;
}
