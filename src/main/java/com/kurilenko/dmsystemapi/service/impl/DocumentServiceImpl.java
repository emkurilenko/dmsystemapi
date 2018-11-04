package com.kurilenko.dmsystemapi.service.impl;

import com.kurilenko.dmsystemapi.dto.ContentTypeDto;
import com.kurilenko.dmsystemapi.dto.DocumentDto;
import com.kurilenko.dmsystemapi.dto.NewDocumentDto;
import com.kurilenko.dmsystemapi.dto.TagDto;
import com.kurilenko.dmsystemapi.entity.ContentType;
import com.kurilenko.dmsystemapi.entity.Document;
import com.kurilenko.dmsystemapi.entity.Tag;
import com.kurilenko.dmsystemapi.exception.DocumentNotFoundException;
import com.kurilenko.dmsystemapi.exception.UnsupportedContentType;
import com.kurilenko.dmsystemapi.repository.DocumentRepository;
import com.kurilenko.dmsystemapi.repository.TagRepository;
import com.kurilenko.dmsystemapi.service.DocumentService;
import com.kurilenko.dmsystemapi.service.TagService;
import org.hibernate.SessionFactory;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class DocumentServiceImpl implements DocumentService {

    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private TagService tagService;

    @Autowired
    private ModelMapper modelMapper;

    private ContentType getContentType(MultipartFile file) throws UnsupportedContentType {
        return ContentType.fromName(file.getContentType()).orElseThrow(() -> new UnsupportedContentType(file.getContentType()));
    }

    @Override
    public Long saveDocument(NewDocumentDto newDocumentDto) throws UnsupportedContentType {
        Document document = new Document();
        document.setDescription(newDocumentDto.getDescription());
        document.setPublisher(newDocumentDto.getPublisher());
        if (newDocumentDto.getCreationDate() == null) {
            document.setCreationDate(new Date());
        } else {
            document.setCreationDate(newDocumentDto.getCreationDate());
        }

        document.setFileName(newDocumentDto.getFile().getOriginalFilename());
        document.setContentType(getContentType(newDocumentDto.getFile()));
        try {
            document.setContent(newDocumentDto.getFile().getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (newDocumentDto.getTags() != null) {
            for (String var :
                    newDocumentDto.getTags()) {
                Tag newTag = tagService.getTagByName(var).orElseGet(() -> {
                    Tag tag = new Tag();
                    tag.setName(var);
                    return tag;
                });
                document.getTags().add(newTag);
                tagService.saveTagWithDocument(newTag);
            }
        }
        document = documentRepository.save(document);

        return document.getId();
    }

    @Override
    public void deleteDocument(Long id) {

    }

    @Override
    public DocumentDto getDocument(Long id) throws DocumentNotFoundException {
        Document doc = documentRepository.findById(id).orElseThrow(() -> new DocumentNotFoundException(id.toString()));
        DocumentDto documentDto = new DocumentDto();
        documentDto.setDescription(doc.getDescription());
        documentDto.setPublisher(doc.getPublisher());
        documentDto.setCreationDate(doc.getCreationDate());
        documentDto.setId(doc.getId());
        documentDto.setFileName(doc.getFileName());
        documentDto.setContentType(new ContentTypeDto(doc.getContentType().getExtension(), doc.getContentType().getMimeType()));

        //Set<TagDto> tags = doc.getTags().stream().map(tag -> modelMapper.map(tag, TagDto.class)).collect(Collectors.toSet());
        documentDto.setTags(null);
        return documentDto;
    }

    @Override
    public List<DocumentDto> getDocuments() {
        return null;
    }

    @Override
    public Long updateDocument() {
        return null;
    }
}
