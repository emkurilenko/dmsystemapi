package com.kurilenko.dmsystemapi.service;

import lombok.RequiredArgsConstructor;
import org.apache.tika.Tika;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TikaService {

    private Tika tika;

    public String detectMediaType(byte[] bytes){
        return tika.detect(bytes);
    }

}
