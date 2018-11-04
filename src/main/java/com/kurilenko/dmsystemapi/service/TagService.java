package com.kurilenko.dmsystemapi.service;

import com.kurilenko.dmsystemapi.dto.TagDto;
import com.kurilenko.dmsystemapi.entity.Tag;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service

public interface TagService {
    Long saveTagWithDocument(Tag tag);
    Optional<Tag> getTagByName(String name);
    void deleteTag(Long id);
    TagDto getTag(Long id);
    List<TagDto> getTags();
}
