package com.kurilenko.dmsystemapi.dto;

import lombok.*;

@Getter
@Setter

@NoArgsConstructor
public class FileContentDto extends FileDto {
    @NonNull
    byte[] content;
}
