package com.kurilenko.dmsystemapi.service;

import com.kurilenko.dmsystemapi.dto.DocumentDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DocumentService {
    Long saveDocument();
    void deleteDocument(Long id);
    DocumentDTO getDocument(Long id);
    List<DocumentDTO> getDocuments();
    Long updateDocument();
}
