package com.epam.java.training.webservices.task2.service;

import com.epam.java.training.webservices.task2.model.File;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface FileService {

    @WebMethod
    File getFile(@WebParam(name = "id") Long id);

    @WebMethod
    File updateFile(@WebParam(name = "id") Long id, @WebParam(name = "file") File file);

    @WebMethod
    File deleteFile(@WebParam(name = "id") Long id);

    @WebMethod
    File createFile(@WebParam(name = "id") Long id, @WebParam(name = "file") File file);
}
