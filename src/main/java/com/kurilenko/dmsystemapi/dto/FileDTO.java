package com.kurilenko.dmsystemapi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter

@NoArgsConstructor
abstract class FileDto {
    @NonNull
    private Long id;

    @NonNull
    private String fileName;

    @NonNull
    private ContentTypeDto contentType;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date creationDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateDate;
}
