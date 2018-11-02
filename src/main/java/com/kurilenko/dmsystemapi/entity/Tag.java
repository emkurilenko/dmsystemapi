package com.kurilenko.dmsystemapi.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Data

@Entity
@Table(name = "tag_table")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "tag_name")
    private String name;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "tags")
    private Set<Document> documents;
}
