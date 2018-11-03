package com.kurilenko.dmsystemapi.service.impl;

import com.kurilenko.dmsystemapi.dto.DocumentDTO;
import com.kurilenko.dmsystemapi.dto.NewDocumentDTO;
import com.kurilenko.dmsystemapi.entity.ContentType;
import com.kurilenko.dmsystemapi.entity.Document;
import com.kurilenko.dmsystemapi.entity.Tag;
import com.kurilenko.dmsystemapi.exception.UnsupportedContentType;
import com.kurilenko.dmsystemapi.repository.DocumentRepository;
import com.kurilenko.dmsystemapi.service.DocumentService;
import com.kurilenko.dmsystemapi.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class DocumentServiceImpl implements DocumentService {

    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private TagService tagService;

    private ContentType getContentType(MultipartFile file) throws UnsupportedContentType {
        return ContentType.fromName(file.getContentType()).orElseThrow(() -> new UnsupportedContentType(file.getContentType()));
    }

    @Override
    public Long saveDocument(NewDocumentDTO newDocumentDTO) throws UnsupportedContentType {
        Document document = new Document();
        document.setDescription(newDocumentDTO.getDescription());
        document.setPublisher(newDocumentDTO.getPublisher());
        if (newDocumentDTO.getCreationDate() == null) {
            document.setCreationDate(new Date());
        } else {
            document.setCreationDate(newDocumentDTO.getCreationDate());
        }

        document.setFileName(newDocumentDTO.getFile().getOriginalFilename());
        document.setContentType(getContentType(newDocumentDTO.getFile()));
        try {
            document.setContent(newDocumentDTO.getFile().getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (newDocumentDTO.getTags() != null) {
            Set<Tag> tags = new HashSet<>();
            for (String var :
                    newDocumentDTO.getTags()) {
                Tag newTag = new Tag();
                newTag.setName(var);
                tags.add(newTag);
            }
            document.setTags(tags);
        } else {
            document.setTags(null);
        }
        document = documentRepository.save(document);

        return document.getId();
    }

    @Override
    public void deleteDocument(Long id) {

    }

    @Override
    public DocumentDTO getDocument(Long id) {
        return null;
    }

    @Override
    public List<DocumentDTO> getDocuments() {
        return null;
    }

    @Override
    public Long updateDocument() {
        return null;
    }
}
