package com.kurilenko.dmsystemapi.service;

import com.kurilenko.dmsystemapi.dto.FileDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FileService {
    Long saveFile();
    void deleteFile(Long id);
    void attach(Long idFile, Long idDocument);
    FileDTO getFile(Long id);
    List<FileDTO> getFiles();
}
