package com.kurilenko.dmsystemapi.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter

@MappedSuperclass
public abstract class File implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "file_name", length = 200)
    @NotNull
    private String fileName;

    @Column(name = "content_type")
    @NotNull
    private ContentType contentType;


    @DateTimeFormat(pattern = "dd.MM.yyyy")
    @Column(name = "update_date")
    @NonNull
    private Date updateDate;

    @DateTimeFormat(pattern = "dd.MM.yyyy")
    @Column(name = "creation_date")
    @NotNull
    private Date creationDate;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @NotNull
    private byte[] content;
}
