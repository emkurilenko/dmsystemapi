package com.kurilenko.dmsystemapi.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Value;

import javax.validation.constraints.NotNull;
import java.util.Set;

@Data

@NoArgsConstructor
public class DocumentDto extends FileDto{
    @NonNull
    String description;

    @NotNull
    String publisher;

    Set<TagDto> tags;
}
