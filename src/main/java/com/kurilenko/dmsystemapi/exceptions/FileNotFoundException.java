package com.kurilenko.dmsystemapi.exceptions;

public class FileNotFoundException extends  Exception{
    private String fileId;

    public FileNotFoundException(String fileId){
        super(String.format("file with ID = " + fileId + " has not been found"));
        this.fileId = fileId;
    }
}
