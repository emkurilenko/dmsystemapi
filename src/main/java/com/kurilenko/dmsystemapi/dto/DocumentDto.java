package com.kurilenko.dmsystemapi.dto;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter

@NoArgsConstructor
public class DocumentDto extends FileDto{
    @NonNull
    String description;

    @NotNull
    String publisher;

    Set<TagDto> tags = new HashSet<>();

}
