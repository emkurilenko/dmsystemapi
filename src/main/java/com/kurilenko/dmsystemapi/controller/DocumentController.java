package com.kurilenko.dmsystemapi.controller;

import com.kurilenko.dmsystemapi.dto.DocumentDTO;
import com.kurilenko.dmsystemapi.dto.NewDocumentDTO;
import com.kurilenko.dmsystemapi.dto.TagDTO;
import com.kurilenko.dmsystemapi.exception.UnsupportedContentType;
import com.kurilenko.dmsystemapi.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/document/")
public class DocumentController {
    @Autowired
    private DocumentService documentService;


    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Long> createNewDocument(@RequestParam(name = "description", required = false) String description,
                                                  @RequestParam(name = "publisher", required = false) String publisher,
                                                  @RequestParam(name = "creationDate", required = false) Date creationDate,
                                                  @RequestParam(name = "file") MultipartFile file,
                                                  @RequestParam(value = "tags[]", required = false) List<String> tags) throws UnsupportedContentType {

        NewDocumentDTO newDocumentDTO = new NewDocumentDTO();
        newDocumentDTO.setPublisher(publisher);
        newDocumentDTO.setDescription(description);
        newDocumentDTO.setFile(file);
        newDocumentDTO.setCreationDate(creationDate);
        newDocumentDTO.setTags(tags);

        Long id = documentService.saveDocument(newDocumentDTO);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }
}
