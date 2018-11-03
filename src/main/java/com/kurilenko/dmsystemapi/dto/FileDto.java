package com.kurilenko.dmsystemapi.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Value;

import java.text.SimpleDateFormat;
import java.util.Date;

@Data

@NoArgsConstructor
public abstract class FileDto {
    private static final SimpleDateFormat dateFormat =
            new SimpleDateFormat("dd-MM-yyyy");
    @NonNull
    private Long id;

    @NonNull
    private String fileName;

    @NonNull
    private ContentTypeDto contentType;

    @NonNull
    private Date creationDate;
}
