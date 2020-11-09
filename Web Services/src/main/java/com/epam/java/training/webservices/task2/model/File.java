package com.epam.java.training.webservices.task2.model;

import lombok.Data;

import javax.xml.bind.annotation.XmlRootElement;

@Data
@XmlRootElement(name = "file")
public class File {
    private Long id;
    private String name;
    private byte[] content;
    private FileType fileType;
}
