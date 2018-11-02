package com.kurilenko.dmsystemapi.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Data

@Entity
@Table(name = "document_table")
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "title_doc", length = 100)
    private String title;

    @NotNull
    @Column(name = "description_doc", length = 4000)
    private String description;

    @NotNull
    @Column(name = "publisher_doc", length = 50)
    private String publisher;

    @OneToOne(mappedBy = "document", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private File file;

    @ManyToMany
    @JoinTable(name = "document_tag",
            joinColumns = @JoinColumn(name = "tag_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "document_id", referencedColumnName = "id"))
    private Set<Tag> tags;


}
