package com.kurilenko.dmsystemapi.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Value;

import java.util.Set;

@Data

@NoArgsConstructor
public class TagDTO {
    @NonNull
    Long id;

    @NonNull
    String name;

    Set<DocumentDTO> documents;
}
