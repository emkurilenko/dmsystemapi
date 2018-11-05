package com.kurilenko.dmsystemapi.service.impl;

import com.kurilenko.dmsystemapi.dto.ContentTypeDto;
import com.kurilenko.dmsystemapi.dto.DocumentDto;
import com.kurilenko.dmsystemapi.dto.TagDto;
import com.kurilenko.dmsystemapi.entity.Document;
import com.kurilenko.dmsystemapi.entity.Tag;
import com.kurilenko.dmsystemapi.exception.TagNotFoundException;
import com.kurilenko.dmsystemapi.repository.DocumentRepository;
import com.kurilenko.dmsystemapi.repository.TagRepository;
import com.kurilenko.dmsystemapi.service.TagService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service

public class TagServiceImpl implements TagService {

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private DocumentRepository documentRepository;

    @Override
    public Long saveTag(Tag tag) {
        Tag tag1 = tagRepository.save(tag);
        return tag1.getId();
    }

    @Override
    public Long saveTag(TagDto tagDto) {
        Tag tag1 = tagRepository.save(convertToEntity(tagDto));
        return tag1.getId();
    }

    @Override
    @Transactional
    public void deleteTag(Long id) throws TagNotFoundException{
        Tag tag = tagRepository.findById(id).orElseThrow(() -> new TagNotFoundException(id.toString()));
        documentRepository.findByTags(tag).get().stream().forEach(document -> document.getTags().remove(tag));
        tagRepository.delete(tag);
    }
    @Override
    @Transactional(readOnly=true)
    public Optional<Tag> getTagByName(String name) {
        return tagRepository.findByName(name);
    }

    @Override
    @Transactional(readOnly=true)
    public TagDto getTag(Long id) throws TagNotFoundException{
        Tag tag = tagRepository.findById(id).orElseThrow(() -> new TagNotFoundException(id.toString()));
        return convertToDto(tag);
    }

    private TagDto convertToDto(Tag tag){
        TagDto tagDto = new TagDto();
        tagDto.setName(tag.getName());
        tagDto.setId(tag.getId());
        tagDto.setDocuments(tag.getDocuments().stream().map(this::convertToDto).collect(Collectors.toSet()));
        return tagDto;
    }

    private DocumentDto convertToDto(Document document) {
        DocumentDto documentDto = modelMapper.map(document, DocumentDto.class);
       // documentDto.setTags(document.getTags().stream().map(tag -> convertToDto(tag)).collect(Collectors.toSet()));
        documentDto.setContentType(new ContentTypeDto(document.getContentType().getExtension(), document.getContentType().getMimeType()));
        return documentDto;
    }

    private Tag convertToEntity(TagDto tagDto){
        Tag tag = modelMapper.map(tagDto, Tag.class);
        return tag;
    }

    @Override
    @Transactional(readOnly=true)
    public List<TagDto> getTags() {
        List<Tag> tags = tagRepository.findAll();
        return tags.stream().map(this::convertToDto).collect(Collectors.toList());
    }
}
