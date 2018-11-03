package com.kurilenko.dmsystemapi.service.impl;

import com.kurilenko.dmsystemapi.dto.TagDTO;
import com.kurilenko.dmsystemapi.entity.Tag;
import com.kurilenko.dmsystemapi.repository.TagRepository;
import com.kurilenko.dmsystemapi.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagRepository tagRepository;

    @Override
    public Long saveTag(TagDTO tagDTO) {
        Tag tag = new Tag();
        tag.setName(tagDTO.getName());
        //tag.setDocuments(tagDTO.getDocuments());
        tag = tagRepository.save(tag);
        return tag.getId();
    }

    @Override
    public void deleteTag(Long id) {

    }

    @Override
    public TagDTO getTag(Long id) {
        return null;
    }

    @Override
    public List<TagDTO> getTags() {
        return null;
    }
}
