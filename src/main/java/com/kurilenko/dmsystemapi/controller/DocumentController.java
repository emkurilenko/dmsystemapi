package com.kurilenko.dmsystemapi.controller;

import com.kurilenko.dmsystemapi.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/document/")
public class DocumentController {
    @Autowired
    private DocumentService documentService;
}
