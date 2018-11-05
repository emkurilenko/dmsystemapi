package com.kurilenko.dmsystemapi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
@Setter

@NoArgsConstructor
public abstract class FileDto {
    @NonNull
    private Long id;

    @NonNull
    private String fileName;

    @NonNull
    private ContentTypeDto contentType;

    @NonNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date creationDate;

    @NonNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateDate;
}
