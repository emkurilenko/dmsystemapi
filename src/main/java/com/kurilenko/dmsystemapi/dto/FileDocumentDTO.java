package com.kurilenko.dmsystemapi.dto;

import lombok.NonNull;
import lombok.Value;

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
    Date creationDate;
}
