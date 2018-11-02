package com.kurilenko.dmsystemapi.dto;

import lombok.NonNull;
import lombok.Value;

import java.util.Set;

@Value
public class DocumentDTO {
    @NonNull
    Long id;

    @NonNull
    String title;

    String description;

    @NonNull
    String publisher;

    FileDTO file;

    Set<TagDTO> tags;
}
