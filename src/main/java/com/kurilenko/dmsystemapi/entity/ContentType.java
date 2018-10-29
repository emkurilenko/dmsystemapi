package com.kurilenko.dmsystemapi.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.Optional;
import java.util.stream.Stream;

@Getter


@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum ContentType {
    WORD_2003_DOC("application/msword", "doc"),
    WORD_2007_DOC("application/vnd.openxmlformats-officedocument.wordprocessingml.document", "docx"),
    EXCEL_2003_SPREADSHEET("application/vnd.ms-excel", "xls"),
    EXCEL_2007_SPREADSHEET("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", "xlsx");

    @NonNull
    private String name;

    @NonNull
    private String extension;

    public static Optional<ContentType> fromName(String name) {
        return Stream.of(values())
                .filter(contentType -> contentType.getName().equals(name))
                .findFirst();
    }
}
