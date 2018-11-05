package com.kurilenko.dmsystemapi.service;

import com.kurilenko.dmsystemapi.dto.TagDto;
import com.kurilenko.dmsystemapi.entity.Tag;
import com.kurilenko.dmsystemapi.exception.TagNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service

public interface TagService {
    Long saveTag(TagDto tagDto);
    Long saveTag(Tag tag);
    Optional<Tag> getTagByName(String name);
    void deleteTag(Long id) throws TagNotFoundException;
    TagDto getTag(Long id) throws TagNotFoundException;
    List<TagDto> getTags();
}
