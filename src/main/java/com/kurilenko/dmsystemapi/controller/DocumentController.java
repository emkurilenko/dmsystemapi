package com.kurilenko.dmsystemapi.controller;

import com.kurilenko.dmsystemapi.dto.DocumentDto;
import com.kurilenko.dmsystemapi.dto.FileContentDto;
import com.kurilenko.dmsystemapi.dto.NewDocumentDto;
import com.kurilenko.dmsystemapi.exception.DocumentNotFoundException;
import com.kurilenko.dmsystemapi.exception.StreamReaderException;
import com.kurilenko.dmsystemapi.exception.UnsupportedContentType;
import com.kurilenko.dmsystemapi.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

@CrossOrigin(allowedHeaders = "*")
@RestController
@RequestMapping("/api/document/")
public class DocumentController {
    @Autowired
    private DocumentService documentService;


    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Long> createNewDocument(@RequestParam(name = "description", required = false, defaultValue = "") String description,
                                                  @RequestParam(name = "publisher", required = false, defaultValue = "user") String publisher,
                                                  @RequestParam(name = "creationDate", required = false) Date creationDate,
                                                  @RequestParam(name = "file") MultipartFile file,
                                                  @RequestParam(value = "tags[]", required = false) List<String> tags) throws UnsupportedContentType, StreamReaderException {
        NewDocumentDto newDocumentDto = new NewDocumentDto();
        newDocumentDto.setPublisher(publisher);
        newDocumentDto.setDescription(description);
        newDocumentDto.setFile(file);
        newDocumentDto.setCreationDate(creationDate);
        newDocumentDto.setTags(tags);

        Long id = documentService.saveDocument(newDocumentDto);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }


    @PostMapping(value = "{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Long> updateDocument(@PathVariable Long id,
                                               @RequestParam(name = "description", required = false) String description,
                                               @RequestParam(name = "publisher", required = false) String publisher,
                                               @RequestParam(name = "creationDate", required = false) Date creationDate,
                                               @RequestParam(name = "file", required = false) MultipartFile file,
                                               @RequestParam(value = "tags[]", required = false) List<String> tags) throws DocumentNotFoundException, UnsupportedContentType, StreamReaderException {
        NewDocumentDto newDocumentDto = new NewDocumentDto();
        newDocumentDto.setId(id);
        newDocumentDto.setPublisher(publisher);
        newDocumentDto.setDescription(description);
        newDocumentDto.setFile(file);
        newDocumentDto.setCreationDate(creationDate);
        newDocumentDto.setTags(tags);
        Long responseId = documentService.updateDocument(newDocumentDto);
        return new ResponseEntity<>(responseId, HttpStatus.OK);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<?> deleteDocument(@PathVariable("id") Long id) throws DocumentNotFoundException {
        documentService.deleteDocument(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<DocumentDto> getDocument(@PathVariable("id") Long id) throws DocumentNotFoundException {
        DocumentDto documentDTO = documentService.getDocument(id);
        return new ResponseEntity<>(documentDTO, HttpStatus.OK);
    }

    @GetMapping(path = "list")
    public ResponseEntity<?> getDocuments() {
        List<DocumentDto> documentDtoList = documentService.getDocuments();
        return new ResponseEntity<>(documentDtoList, HttpStatus.OK);
    }

    @GetMapping(value = "download/{id}")
    public void downloadFile(HttpServletResponse response,
                             @PathVariable("id") Long id) throws DocumentNotFoundException, StreamReaderException {
        FileContentDto documentDto = documentService.getDocumentContent(id);
        response.setContentType(documentDto.getContentType().getExtension());
        response.setHeader("Content-Disposition", String.format("attachment; filename=\"" + documentDto.getFileName() + "." +
                documentDto.getContentType().getExtension() + "\""));


        /* "Content-Disposition : attachment" will be directly download, may provide save as popup, based on your browser setting*/
        //response.setHeader("Content-Disposition", String.format("inline; filename=\"%s\"", file.getName()));

        response.setContentLength(documentDto.getContent().length);

        InputStream inputStream = new ByteArrayInputStream(documentDto.getContent());
        try {
            FileCopyUtils.copy(inputStream, response.getOutputStream());
        } catch (IOException e) {
            throw new StreamReaderException(documentDto.getFileName());
        }
    }


}
