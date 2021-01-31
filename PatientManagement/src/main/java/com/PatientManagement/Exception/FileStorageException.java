package com.PatientManagement.Exception;

public class FileStorageException extends Exception {
    public FileStorageException(String s, Exception ex) {
    }
    public  FileStorageException(String msg){
        super(msg);
    }
}
