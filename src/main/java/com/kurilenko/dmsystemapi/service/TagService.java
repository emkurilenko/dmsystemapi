package com.kurilenko.dmsystemapi.service;

import com.kurilenko.dmsystemapi.dto.TagDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TagService {
    Long saveTag(TagDTO tagDTO);
    void deleteTag(Long id);
    TagDTO getTag(Long id);
    List<TagDTO> getTags();
}
