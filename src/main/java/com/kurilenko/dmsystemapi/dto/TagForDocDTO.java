package com.kurilenko.dmsystemapi.dto;

import lombok.Data;
import lombok.NonNull;

@Data
public class TagForDocDTO {
    @NonNull
    Long idDoc;

    @NonNull
    String nameTag;
}
