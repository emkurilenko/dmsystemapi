package com.kurilenko.dmsystemapi.repository;

import com.kurilenko.dmsystemapi.entity.FileDocument;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileDocumentRepositpry extends JpaRepository<FileDocument, Long> {
    FileDocument findByName(String name);
}
