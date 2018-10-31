package com.kurilenko.dmsystemapi.entity;

import lombok.Data;

import javax.persistence.*;

@Data

@Entity
@Table(name = "file")
public class File {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Lob
    byte[] file;
}
