package com.kurilenko.dmsystemapi.repository;

import com.kurilenko.dmsystemapi.entity.FileDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface FileDocumentRepository extends JpaRepository<FileDocument, Long> {
    Optional<FileDocument> findByName(String name);
}
