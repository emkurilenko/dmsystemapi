package com.kurilenko.dmsystemapi.service.impl;

import com.kurilenko.dmsystemapi.dto.FileDocumentDTO;
import com.kurilenko.dmsystemapi.dto.NewFileDocument;
import com.kurilenko.dmsystemapi.entity.ContentType;
import com.kurilenko.dmsystemapi.entity.FileDocument;
import com.kurilenko.dmsystemapi.exceptions.FileNotFoundException;
import com.kurilenko.dmsystemapi.exceptions.UnsupportedContentType;
import com.kurilenko.dmsystemapi.repository.FileDocumentRepository;
import com.kurilenko.dmsystemapi.service.FileDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class FileDocumentServiceImpl implements FileDocumentService {

    @Autowired
    private FileDocumentRepository fileDocumentRepository;

    private ContentType getContentType(MultipartFile file) throws UnsupportedContentType {
        return ContentType.fromName(file.getContentType()).orElseThrow(() -> new UnsupportedContentType(file.getContentType()));
    }

    @Override
    public Long createNewFileDocument(@Valid NewFileDocument newFileDocument) throws UnsupportedContentType {
        FileDocument fileDocument = new FileDocument();
        fileDocument.setName(newFileDocument.getName());
        fileDocument.setDescription(newFileDocument.getDescription());
        try {
            fileDocument.setContentType(getContentType(newFileDocument.getFile()));
            fileDocument.setFileContent(newFileDocument.getFile().getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        fileDocument.setCreationDate(new Date());
        fileDocument = fileDocumentRepository.save(fileDocument);
        return fileDocument.getId();
    }

    @Override
    public FileDocumentDTO getFileDocumentDTO(Long id) throws FileNotFoundException {
        FileDocument fileDocument = fileDocumentRepository.findById(id).orElseThrow(() -> new FileNotFoundException(id.toString()));

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
        return fileDocumentRepository.findById(id).orElseThrow(() -> new FileNotFoundException(id.toString()));
    }


    //Грубый костыль! Exception вылитает
    @Override
    public FileDocument getFileDocumentForName(String fileName) throws FileNotFoundException {
        return fileDocumentRepository.findAll()
                .stream().filter(s->s.getName().contains(fileName)).findAny()
                .orElseThrow(() -> new FileNotFoundException(fileName));
    }

    @Override
    public void deleteFile(Long id) {
        fileDocumentRepository.deleteById(id);
    }
}
