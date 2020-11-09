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
@Entity(name = "FRIENDSHIPS")
public class Friendship {
    @Column(name = "USER_ID1")
    private Long userId1;
    @Column(name = "USER_ID2")
    private Long userId2;
    @Column(name = "TIMESTAMP")
    private Timestamp timestamp;

}
