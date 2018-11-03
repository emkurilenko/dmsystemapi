package com.kurilenko.dmsystemapi.service;

import com.kurilenko.dmsystemapi.dto.DocumentDTO;
import com.kurilenko.dmsystemapi.dto.NewDocumentDTO;
import com.kurilenko.dmsystemapi.exception.UnsupportedContentType;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DocumentService {
    Long saveDocument(NewDocumentDTO newDocumentDTO) throws UnsupportedContentType;
    void deleteDocument(Long id);
    DocumentDTO getDocument(Long id);
    List<DocumentDTO> getDocuments();
    Long updateDocument();
}
