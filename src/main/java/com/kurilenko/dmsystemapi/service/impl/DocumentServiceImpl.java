package com.kurilenko.dmsystemapi.service.impl;

import com.kurilenko.dmsystemapi.dto.*;
import com.kurilenko.dmsystemapi.entity.ContentType;
import com.kurilenko.dmsystemapi.entity.Document;
import com.kurilenko.dmsystemapi.entity.Tag;
import com.kurilenko.dmsystemapi.exception.DocumentNotFoundException;
import com.kurilenko.dmsystemapi.exception.StreamReaderException;
import com.kurilenko.dmsystemapi.exception.TagNotFoundException;
import com.kurilenko.dmsystemapi.exception.UnsupportedContentType;
import com.kurilenko.dmsystemapi.repository.DocumentRepository;
import com.kurilenko.dmsystemapi.service.DocumentService;
import com.kurilenko.dmsystemapi.service.TagService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class DocumentServiceImpl implements DocumentService {


    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private TagService tagService;


    private ContentType getContentType(MultipartFile file) throws UnsupportedContentType {
        return ContentType.fromName(file.getContentType()).orElseThrow(() -> new UnsupportedContentType(file.getContentType()));
    }

    @Override
    public Long saveDocument(NewDocumentDto newDocumentDto) throws UnsupportedContentType, StreamReaderException {
        final Document document = new Document();
        document.setDescription(newDocumentDto.getDescription());
        document.setPublisher(newDocumentDto.getPublisher());
        if (newDocumentDto.getCreationDate() == null) {
            document.setCreationDate(new Date());
            document.setUpdateDate(new Date());
        } else {
            document.setCreationDate(newDocumentDto.getCreationDate());
            document.setUpdateDate(newDocumentDto.getCreationDate());
        }
        document.setFileName(newDocumentDto.getFile().getOriginalFilename());
        document.setContentType(getContentType(newDocumentDto.getFile()));
        try {
            document.setContent(newDocumentDto.getFile().getBytes());
        } catch (IOException e) {
            throw new StreamReaderException(document.getFileName());
        }
        newDocumentDto.getTags().forEach(var -> {
            /*
            Tag newTag = tagService.getTagByName(var).orElseGet(() -> {
                Tag tag = new Tag();
                tag.setName(var);
                return tag;
            });
            document.getTags().add(newTag);
            tagService.saveTag(newTag);
            */
            document.getTags().add(tagService.saveTag(var));
        });
        return documentRepository.save(document).getId();
    }


    @Override
    @Transactional
    public void deleteDocument(Long id) throws DocumentNotFoundException {
        Document document = documentRepository.findById(id).orElseThrow(() -> new DocumentNotFoundException(id.toString()));
        documentRepository.delete(document); //Как лучше удалять по entity или по ID? Или все зависит от ситуации?
    }

    @Override
    @Transactional(readOnly = true)
    public DocumentDto getDocument(Long id) throws DocumentNotFoundException {
        Document doc = documentRepository.findById(id).orElseThrow(() -> new DocumentNotFoundException(id.toString()));
        return convertToDto(doc);
    }

    @Override
    public FileContentDto getDocumentContent(Long id) throws DocumentNotFoundException {
        Document doc = documentRepository.findById(id).orElseThrow(() -> new DocumentNotFoundException(id.toString()));
        return convertToDtoContent(doc);
    }


    private FileContentDto convertToDtoContent(Document document) {
        FileContentDto fileContent = new FileContentDto();
        fileContent.setContent(document.getContent());
        fileContent.setContentType(new ContentTypeDto(document.getContentType().getExtension(), document.getContentType().getMimeType()));
        fileContent.setCreationDate(document.getCreationDate());
        fileContent.setFileName(document.getFileName());
        fileContent.setId(document.getId());
        return fileContent;
    }

    private DocumentDto convertToDto(Document document) {
        DocumentDto documentDto = modelMapper.map(document, DocumentDto.class);
        documentDto.setTags(document.getTags().stream().map(tag -> convertToDto(tag)).collect(Collectors.toSet()));
        documentDto.setContentType(new ContentTypeDto(document.getContentType().getExtension(), document.getContentType().getMimeType()));
        return documentDto;
    }

    /*
     * Костыль! Можно было использовать mapper и на свойство Set<Document> повесить анностацию @JsonIgnore
     * Я так и сделал, но думаю что это не правильно
     * */
    private TagDto convertToDto(Tag tag) {
        TagDto tagDto = modelMapper.map(tag, TagDto.class);
        return tagDto;
    }

    @Override
    @Transactional(readOnly = true)
    public List<DocumentDto> getDocuments() {
        List<DocumentDto> list = new ArrayList<>();
        documentRepository.findAll().forEach(var -> list.add(convertToDto(var)));
        return list;
    }

    @Override
    public Long updateDocument(NewDocumentDto documentDto) throws DocumentNotFoundException, UnsupportedContentType, StreamReaderException {
        Document doc = documentRepository.findById(documentDto.getId()).orElseThrow(() -> new DocumentNotFoundException(documentDto.getId().toString()));
        doc.setUpdateDate(new Date());
        doc.setPublisher(documentDto.getPublisher());
        doc.setDescription(documentDto.getDescription());
        if (documentDto.getFile() != null) {
            doc.setFileName(documentDto.getFile().getOriginalFilename());
            doc.setContentType(getContentType(documentDto.getFile()));
            try {
                doc.setContent(documentDto.getFile().getBytes());
            } catch (IOException e) {
                throw new StreamReaderException(doc.getFileName());
            }
        }
        doc.getTags().clear();
        documentDto.getTags().forEach(var -> {
            doc.getTags().add(tagService.saveTag(var));
        });
        return documentRepository.save(doc).getId();
    }

    @Override
    public Long attachTag(TagForDocDTO tagForDocDTO) throws DocumentNotFoundException {
        Document document = documentRepository.findById(tagForDocDTO.getIdDoc()).orElseThrow(() -> new DocumentNotFoundException(tagForDocDTO.getIdDoc().toString()));
        document.getTags().add(tagService.saveTag(tagForDocDTO.getNameTag()));
        return documentRepository.save(document).getId();
    }

    @Override
    public Long unfastenTag(TagForDocDTO tagForDocDTO) throws DocumentNotFoundException, TagNotFoundException {
        System.out.println(tagForDocDTO.getNameTag());
        System.out.println(tagForDocDTO.getIdDoc());
        Document document = documentRepository.findById(tagForDocDTO.getIdDoc()).orElseThrow(() -> new DocumentNotFoundException(tagForDocDTO.getIdDoc().toString()));
        document.getTags().remove(tagService.getTagByName(tagForDocDTO.getNameTag()).orElseThrow(() -> new TagNotFoundException(tagForDocDTO.getNameTag())));
        return document.getId();
    }
}
