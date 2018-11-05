package com.kurilenko.dmsystemapi.service;

import com.kurilenko.dmsystemapi.dto.DocumentDto;
import com.kurilenko.dmsystemapi.dto.FileContentDto;
import com.kurilenko.dmsystemapi.dto.NewDocumentDto;
import com.kurilenko.dmsystemapi.exception.DocumentNotFoundException;
import com.kurilenko.dmsystemapi.exception.StreamReaderException;
import com.kurilenko.dmsystemapi.exception.UnsupportedContentType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service

public interface DocumentService {
    Long saveDocument(NewDocumentDto newDocumentDTO) throws UnsupportedContentType, StreamReaderException;
    void deleteDocument(Long id) throws DocumentNotFoundException;
    DocumentDto getDocument(Long id) throws DocumentNotFoundException;
    FileContentDto getDocumentContent(String fileName) throws DocumentNotFoundException;
    List<DocumentDto> getDocuments();
    Long updateDocument();
}
