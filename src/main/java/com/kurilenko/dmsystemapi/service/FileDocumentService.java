package com.kurilenko.dmsystemapi.service;

import com.kurilenko.dmsystemapi.dto.FileDocumentDTO;
import com.kurilenko.dmsystemapi.dto.NewFileDocument;
import com.kurilenko.dmsystemapi.entity.FileDocument;
import com.kurilenko.dmsystemapi.exceptions.FileNotFoundException;
import com.kurilenko.dmsystemapi.exceptions.UnsupportedContentType;
import com.kurilenko.dmsystemapi.repository.FileDocumentRepositpry;
import org.springframework.stereotype.Service;

import javax.validation.Valid;

@Service
public interface FileDocumentService {
    void createNewFileDocument(@Valid NewFileDocument newFileDocument)  throws UnsupportedContentType;

    FileDocumentDTO getFileDocumentDTO(Long id) throws FileNotFoundException;

    FileDocument getFileDocument(Long id) throws FileNotFoundException ;

    void deleteFile(Long id);


}
