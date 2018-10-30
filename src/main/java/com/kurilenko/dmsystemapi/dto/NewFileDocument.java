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

    public NewFileDocument(@Length(max = 255) String name, @Length(max = 1024) String description, MultipartFile file) {
        this.name = name;
        this.description = description;
        this.file = file;
    }
}
