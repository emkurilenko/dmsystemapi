package com.kurilenko.dmsystemapi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Value;

import java.text.SimpleDateFormat;
import java.util.Date;

@Data

@NoArgsConstructor
public abstract class FileDto {
    @NonNull
    private Long id;

    @NonNull
    private String fileName;

    @NonNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private ContentTypeDto contentType;

    @NonNull
    private Date creationDate;
}
