package com.kurilenko.dmsystemapi.controller;

import com.kurilenko.dmsystemapi.dto.DocumentDto;
import com.kurilenko.dmsystemapi.dto.NewDocumentDto;
import com.kurilenko.dmsystemapi.exception.DocumentNotFoundException;
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
        NewDocumentDto newDocumentDto = new NewDocumentDto();
        newDocumentDto.setPublisher(publisher);
        newDocumentDto.setDescription(description);
        newDocumentDto.setFile(file);
        newDocumentDto.setCreationDate(creationDate);
        newDocumentDto.setTags(tags);

        Long id = documentService.saveDocument(newDocumentDto);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @DeleteMapping(value = {"id"})
    public ResponseEntity<?> deleteDocument(@PathVariable("id") Long id){
        documentService.deleteDocument(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<DocumentDto> getDocument(@PathVariable("id") Long id) throws DocumentNotFoundException {
        DocumentDto documentDTO = documentService.getDocument(id);
        return new ResponseEntity<>(documentDTO, HttpStatus.OK);
    }

}
