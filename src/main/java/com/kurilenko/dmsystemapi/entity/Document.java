package com.kurilenko.dmsystemapi.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Getter
@Setter

@Entity
@Table(name = "document_table")
public class Document extends File {


    @Column(name = "description_doc", length = 2500)
    private String description;


    @Column(name = "publisher_doc", length = 50)
    private String publisher;

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinTable(name = "document_tag",
            joinColumns = @JoinColumn(name = "document_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id", referencedColumnName = "id"))
    private Set<Tag> tags = new HashSet<>(0);
}