package com.kurilenko.dmsystemapi.controller;

import com.kurilenko.dmsystemapi.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tags/")
public class TagController {

    @Autowired
    private TagService tagService;

}
