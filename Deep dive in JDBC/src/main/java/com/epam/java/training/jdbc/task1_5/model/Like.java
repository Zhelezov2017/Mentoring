package com.epam.java.training.jdbc.task1_5.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "LIKES")
public class Like {
    @Column(name = "POST_ID")
    private Long postId;
    @Column(name = "USER_ID")
    private Long userId;
    @Column(name = "TIMESTAMP")
    private Timestamp timestamp;
}
