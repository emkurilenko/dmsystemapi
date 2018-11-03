package com.kurilenko.dmsystemapi.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Value;

import java.util.Set;

@Data

@NoArgsConstructor
public class DocumentDTO extends FileDTO{

    String description;

    String publisher;

    String[] tags;
}
