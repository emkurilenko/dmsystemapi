package com.kurilenko.dmsystemapi.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Value;

import java.util.Date;

@Data
@NoArgsConstructor
public abstract class FileDTO {
    @NonNull
    Long id;

    @NonNull
    String fileName;

    @NonNull
    ContentTypeDTO contentType;

    @NonNull
    Date creationDate;

    byte[] content;
}
