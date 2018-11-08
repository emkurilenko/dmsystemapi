package com.kurilenko.dmsystemapi.service;

import com.kurilenko.dmsystemapi.dto.DocumentDto;
import com.kurilenko.dmsystemapi.dto.FileContentDto;
import com.kurilenko.dmsystemapi.dto.NewDocumentDto;
import com.kurilenko.dmsystemapi.dto.TagForDocDTO;
import com.kurilenko.dmsystemapi.exception.DocumentNotFoundException;
import com.kurilenko.dmsystemapi.exception.StreamReaderException;
import com.kurilenko.dmsystemapi.exception.TagNotFoundException;
import com.kurilenko.dmsystemapi.exception.UnsupportedContentType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public interface DocumentService {
    Long saveDocument(NewDocumentDto newDocumentDTO) throws UnsupportedContentType, StreamReaderException;
    void deleteDocument(Long id) throws DocumentNotFoundException;
    DocumentDto getDocument(Long id) throws DocumentNotFoundException;
    FileContentDto getDocumentContent(Long id) throws DocumentNotFoundException;
    List<DocumentDto> getDocuments();
    Long updateDocument(DocumentDto documentDto) throws DocumentNotFoundException;

    Long attachTag(TagForDocDTO tagForDocDTO) throws DocumentNotFoundException;
    Long unfastenTag(TagForDocDTO tagForDocDTO) throws DocumentNotFoundException, TagNotFoundException;


    Page<DocumentDto> getPage(PageRequest pageRequest);
}
