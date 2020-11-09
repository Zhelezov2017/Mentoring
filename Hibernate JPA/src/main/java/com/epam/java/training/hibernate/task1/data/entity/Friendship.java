package com.epam.java.training.hibernate.task1.data.entity;

import com.epam.java.training.hibernate.task1.data.entity.id.FriendshipId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@NoArgsConstructor
@Data
@EqualsAndHashCode
@Entity
@IdClass(FriendshipId.class)
@Table(name = "FRIENDSHIPS")
public class Friendship {
    @Id
    @Column(name = "USER_ID1", nullable = false)
    private Long userId1;
    @Id
    @Column(name = "USER_ID2", nullable = false)
    private Long userId2;
    @Column(name = "TIMESTAMP")
    private Timestamp timestamp;
}
