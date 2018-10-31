package com.kurilenko.dmsystemapi.service;

import com.kurilenko.dmsystemapi.dto.FileDocumentDTO;
import com.kurilenko.dmsystemapi.dto.NewFileDocument;
import com.kurilenko.dmsystemapi.entity.FileDocument;
import com.kurilenko.dmsystemapi.exceptions.FileNotFoundException;
import com.kurilenko.dmsystemapi.exceptions.UnsupportedContentType;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@Service
public interface FileDocumentService {
    Long createNewFileDocument(@Valid NewFileDocument newFileDocument)  throws UnsupportedContentType;

    FileDocumentDTO getFileDocumentDTO(Long id) throws FileNotFoundException;

    List<FileDocumentDTO> getAllFiles();


    FileDocument getFileDocument(Long id) throws FileNotFoundException;
    FileDocument getFileDocumentForName(String fileName) throws FileNotFoundException;
    void deleteFile(Long id);


}
