package com.kurilenko.dmsystemapi.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

@Data

@NoArgsConstructor
public class NewDocumentDTO {
    MultipartFile file;
    String publisher;
    String description;
    List<String> tags;
    Date creationDate;
}
