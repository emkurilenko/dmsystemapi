package com.kurilenko.dmsystemapi.service.impl;

import com.kurilenko.dmsystemapi.dto.FileDocumentDTO;
import com.kurilenko.dmsystemapi.dto.NewFileDocument;
import com.kurilenko.dmsystemapi.entity.ContentType;
import com.kurilenko.dmsystemapi.entity.FileDocument;
import com.kurilenko.dmsystemapi.exceptions.FileNotFoundException;
import com.kurilenko.dmsystemapi.exceptions.UnsupportedContentType;
import com.kurilenko.dmsystemapi.repository.FileDocumentRepositpry;
import com.kurilenko.dmsystemapi.service.FileDocumentService;
import com.kurilenko.dmsystemapi.service.TikaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Date;

@Service
public class FileDocumentServiceImpl implements FileDocumentService {

    @Autowired
    private FileDocumentRepositpry fileDocumentRepositpry;

    @Autowired
    private TikaService tikaService;

    private ContentType getContentType(byte[] file) throws UnsupportedContentType {
        String contentType = tikaService.detectMediaType(file);
        return ContentType.fromName(contentType).orElseThrow(() -> new UnsupportedContentType(contentType));
    }

    @Override
    public void createNewFileDocument(@Valid NewFileDocument newFileDocument) throws UnsupportedContentType {
        FileDocument fileDocument = new FileDocument();
        fileDocument.setName(newFileDocument.getName());
        fileDocument.setDescription(newFileDocument.getDescription());
        try {
            fileDocument.setContentType(getContentType(newFileDocument.getFile().getBytes()));
            ByteBuffer content = ByteBuffer.wrap(newFileDocument.getFile().getBytes());
            fileDocument.setFileContent(content);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        fileDocument.setCreationDate(new Date());
    }

    @Override
    public FileDocumentDTO getFileDocumentDTO(Long id) throws FileNotFoundException {
        FileDocument fileDocument = fileDocumentRepositpry.findById(id).orElseThrow(() -> new FileNotFoundException(id.toString()));

        return new FileDocumentDTO(
                fileDocument.getId(),
                fileDocument.getName(),
                fileDocument.getContentType().name(),
                fileDocument.getContentType().getExtension(),
                fileDocument.getCreationDate()
        );
    }

    @Override
    public FileDocument getFileDocument(Long id) throws FileNotFoundException {
        return fileDocumentRepositpry.findById(id).orElseThrow(() -> new FileNotFoundException(id.toString()));
    }

    @Override
    public void deleteFile(Long id) {
        fileDocumentRepositpry.deleteById(id);
    }
}
