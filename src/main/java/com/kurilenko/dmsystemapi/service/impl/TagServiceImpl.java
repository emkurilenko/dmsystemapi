package com.kurilenko.dmsystemapi.service.impl;

import com.kurilenko.dmsystemapi.dto.TagDto;
import com.kurilenko.dmsystemapi.entity.Tag;
import com.kurilenko.dmsystemapi.repository.TagRepository;
import com.kurilenko.dmsystemapi.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service

public class TagServiceImpl implements TagService {

    @Autowired
    private TagRepository tagRepository;

    @Override
    public Long saveTagWithDocument(Tag tag) {
        Tag tag1 = tagRepository.save(tag);
        return tag1.getId();
    }

    @Override
    public void deleteTag(Long id) {

    }
    @Override
    @Transactional(readOnly=true)
    public Optional<Tag> getTagByName(String name) {
        return tagRepository.findByName(name);
    }

    @Override
    public TagDto getTag(Long id) {
        return null;
    }

    @Override
    public List<TagDto> getTags() {
        return null;
    }
}
