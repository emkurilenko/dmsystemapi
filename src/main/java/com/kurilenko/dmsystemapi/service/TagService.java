package com.kurilenko.dmsystemapi.service;

import com.kurilenko.dmsystemapi.dto.TagDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TagService {
    Long saveTag(TagDto tagDTO);
    void deleteTag(Long id);
    TagDto getTag(Long id);
    List<TagDto> getTags();
}
