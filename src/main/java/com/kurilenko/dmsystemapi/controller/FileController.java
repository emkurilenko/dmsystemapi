package com.kurilenko.dmsystemapi.controller;

import com.kurilenko.dmsystemapi.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/files/")
public class FileController {
    @Autowired
    private FileService fileService;
}
