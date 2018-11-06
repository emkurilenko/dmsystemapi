package com.kurilenko.dmsystemapi.controller;

import com.kurilenko.dmsystemapi.dto.TagDto;
import com.kurilenko.dmsystemapi.exception.TagNotFoundException;
import com.kurilenko.dmsystemapi.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(allowedHeaders = "*")
@RestController
@RequestMapping("/api/tag/")
public class TagController {

    @Autowired
    private TagService tagService;

    @PostMapping
    public ResponseEntity<Long> createTag(@RequestParam(value = "name") String name) {
        TagDto tagDTO = new TagDto();
        tagDTO.setName(name);
        Long id = tagService.saveTag(tagDTO);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<?> deleteTag(@PathVariable(value = "id") Long id) throws TagNotFoundException{
        tagService.deleteTag(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(path = "list")
    public ResponseEntity<List<TagDto>> getsAllTags(){
        List<TagDto> tagDTOS = tagService.getTags();
        return new ResponseEntity<>(tagDTOS, HttpStatus.OK);
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<TagDto> getTag(@PathVariable(value = "id") Long id) throws TagNotFoundException {
        TagDto tagDTO = tagService.getTag(id);
        return new ResponseEntity<>(tagDTO, HttpStatus.OK);
    }
}
