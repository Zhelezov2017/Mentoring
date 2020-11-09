package com.epam.java.training.jdbc.task1_5.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "POSTS")
public class Post {
    @Id
    @Column(name = "ID", nullable = false)
    private Long id;
    @Column(name = "USER_ID")
    private Long userId;
    @Column(name = "TEXT")
    private String text;
    @Column(name = "TIMESTAMP")
    private Timestamp timestamp;

}
