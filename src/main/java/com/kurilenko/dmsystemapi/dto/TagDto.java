package com.kurilenko.dmsystemapi.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

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
