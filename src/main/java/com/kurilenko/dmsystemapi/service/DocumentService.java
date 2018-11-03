package com.kurilenko.dmsystemapi.service;

import com.kurilenko.dmsystemapi.dto.DocumentDto;
import com.kurilenko.dmsystemapi.dto.NewDocumentDto;
import com.kurilenko.dmsystemapi.exception.DocumentNotFoundException;
import com.kurilenko.dmsystemapi.exception.UnsupportedContentType;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DocumentService {
    Long saveDocument(NewDocumentDto newDocumentDTO) throws UnsupportedContentType;
    void deleteDocument(Long id);
    DocumentDto getDocument(Long id) throws DocumentNotFoundException;
    List<DocumentDto> getDocuments();
    Long updateDocument();
}
