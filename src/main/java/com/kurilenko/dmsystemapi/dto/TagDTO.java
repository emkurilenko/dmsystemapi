package com.kurilenko.dmsystemapi.dto;

import lombok.NonNull;
import lombok.Value;

import java.util.Set;

@Value
public class TagDTO {
    @NonNull
    Long id;

    @NonNull
    String name;

    Set<DocumentDTO> documents;
}
