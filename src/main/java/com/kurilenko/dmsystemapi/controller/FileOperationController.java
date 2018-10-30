package com.kurilenko.dmsystemapi.controller;

import com.kurilenko.dmsystemapi.dto.NewFileDocument;
import com.kurilenko.dmsystemapi.entity.FileDocument;
import com.kurilenko.dmsystemapi.exceptions.FileNotFoundException;
import com.kurilenko.dmsystemapi.exceptions.UnsupportedContentType;
import com.kurilenko.dmsystemapi.service.FileDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.UUID;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/files")
public class FileOperationController {

    @Autowired
    private FileDocumentService fileDocumentService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
    public String processNewFileForm(@RequestParam("name") String name,
                                     @RequestParam(name = "description", required = false) String description,
                                     @RequestParam("file") MultipartFile file) throws UnsupportedContentType {
        NewFileDocument newFileDocument = new NewFileDocument(name, description, file);
        Long fileID = fileDocumentService.createNewFileDocument(newFileDocument);
        return fileID.toString();
    }
/*
    @GetMapping(value = "/{file_name}")
    public FileSystemResource getFile(@PathVariable("file_name") String fileName) throws FileNotFoundException {
        File file = new File();
        return new FileSystemResource();
    }*/


    @RequestMapping(value="/{fileName}", method = RequestMethod.GET)
    public void downloadFile(HttpServletResponse response,
                             @PathVariable("fileName")String fileName) throws FileNotFoundException, IOException {

        FileDocument fileDocument = fileDocumentService.getFileDocumentForName(fileName);
        response.setContentType(fileDocument.getContentType().getExtension());

        /* "Content-Disposition : inline" will show viewable types [like images/text/pdf/anything viewable by browser] right on browser
            while others(zip e.g) will be directly downloaded [may provide save as popup, based on your browser setting.]*/
        response.setHeader("Content-Disposition", String.format("attachment; filename=\"" + fileDocument.getName() + "."+
                fileDocument.getContentType().getExtension() +"\""));


        /* "Content-Disposition : attachment" will be directly download, may provide save as popup, based on your browser setting*/
        //response.setHeader("Content-Disposition", String.format("inline; filename=\"%s\"", file.getName()));

        response.setContentLength(fileDocument.getFileContent().length);

        InputStream inputStream = new ByteArrayInputStream(fileDocument.getFileContent());

        //Copy bytes from source to destination(outputstream in this example), closes both streams.
        FileCopyUtils.copy(inputStream, response.getOutputStream());
    }


}
