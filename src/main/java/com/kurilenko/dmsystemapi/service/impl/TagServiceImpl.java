package com.kurilenko.dmsystemapi.service.impl;

import com.kurilenko.dmsystemapi.dto.TagDTO;
import com.kurilenko.dmsystemapi.service.TagService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImpl implements TagService {
    @Override
    public Long saveTag(TagDTO tagDTO) {
        return null;
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
