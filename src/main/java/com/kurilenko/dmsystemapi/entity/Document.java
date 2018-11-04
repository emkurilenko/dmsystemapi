package com.kurilenko.dmsystemapi.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.*;

@Getter
@Setter

@Entity
@Table(name = "document_table")

public class Document extends File {


    @Column(name = "description_doc", length = 4000)
    private String description;


    @Column(name = "publisher_doc", length = 50)
    private String publisher;

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinTable(name = "document_tag",
            joinColumns = @JoinColumn(name = "document_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id", referencedColumnName = "id"))
    private Set<Tag> tags = new HashSet<>(0);
/*

    public void addTag(Tag tag){
        if(!tags.contains(tag)){
            tags.add(tag);
            tag.addDoc(this);
        }
    }*/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Document document = (Document) o;
        return Objects.equals(description, document.description) &&
                Objects.equals(publisher, document.publisher);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), description, publisher);
    }
}