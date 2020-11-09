package com.epam.java.training.hibernate.task1.data.entity.id;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@EqualsAndHashCode
public class FriendshipId implements Serializable {
    private Long userId1;
    private Long userId2;
}
