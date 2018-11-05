package com.kurilenko.dmsystemapi.repository;

import com.kurilenko.dmsystemapi.entity.Document;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DocumentRepository extends PagingAndSortingRepository<Document, Long> {
    Optional<Document> findByFileName(String fileName);
}
