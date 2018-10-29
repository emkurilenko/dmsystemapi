package com.kurilenko.dmsystemapi.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

@NoArgsConstructor
@Data
public class NewFileDocument {

    @Length(max = 255)
    private String name = "";

    @Length(max = 1024)
    private String description;

    private MultipartFile file;
}
