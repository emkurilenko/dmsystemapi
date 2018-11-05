package com.kurilenko.dmsystemapi.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter

@NoArgsConstructor
public class TagDto {
    @NonNull
    Long id;


    @NonNull
    String name;

    @JsonIgnore
    Set<DocumentDto> documents = new HashSet<>();
}
