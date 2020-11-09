package com.epam.java.training.hibernate.task1.data.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.sql.Timestamp;

@NoArgsConstructor
@Data
@EqualsAndHashCode
@Entity
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "LIKES")
public class Like {
    @Id
    @Column(name = "ID", nullable = false)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "POST_ID", nullable = false)
    private Post post;
    @Column(name = "USER_ID", nullable = false)
    private Long userId;
    @Column(name = "TIMESTAMP")
    private Timestamp timestamp;
}
