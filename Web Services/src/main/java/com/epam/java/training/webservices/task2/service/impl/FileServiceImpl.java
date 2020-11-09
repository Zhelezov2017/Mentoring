package com.epam.java.training.webservices.task2.service.impl;

import com.epam.java.training.webservices.task2.model.File;
import com.epam.java.training.webservices.task2.service.FileService;

import javax.jws.WebService;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@WebService(endpointInterface = "com.epam.java.training.webservices.task2.service.FileService")
public class FileServiceImpl implements FileService {

    private static final Map<Long, File> fileMap = new HashMap<>();

    @Override
    public File getFile(Long id) {
        return fileMap.get(id);
    }

    @Override
    public File updateFile(Long id, File file) {
        File fileFromMap = fileMap.get(file.getId());
        if (Objects.isNull(fileFromMap)) {
            throw new NullPointerException("File doesn't found with id:" + file.getId());
        }
        fileFromMap.setName(file.getName());
        fileFromMap.setContent(file.getContent());
        fileFromMap.setFileType(file.getFileType());
        return fileMap.put(fileFromMap.getId(), fileFromMap);
    }

    @Override
    public File deleteFile(Long id) {
        return fileMap.remove(id);
    }

    @Override
    public File createFile(Long id, File file) {
        return fileMap.put(file.getId(), file);
    }
}
