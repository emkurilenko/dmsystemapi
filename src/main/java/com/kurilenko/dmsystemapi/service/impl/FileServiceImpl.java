package com.kurilenko.dmsystemapi.service.impl;

import com.kurilenko.dmsystemapi.dto.FileDTO;
import com.kurilenko.dmsystemapi.service.FileService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileServiceImpl implements FileService {
    @Override
    public Long saveFile() {
        return null;
    }

    @Override
    public void deleteFile(Long id) {

    }

    @Override
    public void attach(Long idFile, Long idDocument) {

    }

    @Override
    public FileDTO getFile(Long id) {
        return null;
    }

    @Override
    public List<FileDTO> getFiles() {
        return null;
    }
}
