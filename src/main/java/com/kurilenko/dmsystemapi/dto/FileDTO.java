package com.kurilenko.dmsystemapi.dto;

import lombok.NonNull;
import lombok.Value;

import java.util.Date;

@Value
public class FileDTO {
    @NonNull
    Long id;

    @NonNull
    String name;

    @NonNull
    ContentTypeDTO contentType;

    @NonNull
    Date creationDate;

    @NonNull
    byte[] content;

    DocumentDTO documentDTO;
}
