package com.kurilenko.dmsystemapi.entity;

import lombok.Data;

import javax.persistence.*;
import java.nio.ByteBuffer;
import java.util.Date;

@Data

@Entity
@Table(name = "file_document")
public class FileDocument {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "content_type")
    private ContentType contentType;

    @Column(name = "creation_date")
    private Date creationDate;

    @Lob
    @Column(name = "file_content")
    private ByteBuffer fileContent;

    @Column(name = "description")
    private String description;
}
