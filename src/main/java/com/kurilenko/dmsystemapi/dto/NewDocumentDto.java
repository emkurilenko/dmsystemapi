package com.kurilenko.dmsystemapi.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

@Data

@NoArgsConstructor
public class NewDocumentDto {
    Long id;
    MultipartFile file;
    @NonNull
    String publisher;
    @NonNull
    String description;
    @NonNull
    List<String> tags;
    Date creationDate;
    Date updateDate;
}
