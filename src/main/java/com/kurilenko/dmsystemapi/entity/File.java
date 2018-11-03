package com.kurilenko.dmsystemapi.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Data

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

    @Column(name = "creation_date")
    @NotNull
    private Date creationDate;

    @Lob
   // @Basic(fetch = FetchType.LAZY)
    @NotNull
    private byte[] content;
}
