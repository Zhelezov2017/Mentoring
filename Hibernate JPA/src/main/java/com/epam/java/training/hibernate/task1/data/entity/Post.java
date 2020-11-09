package com.epam.java.training.hibernate.task1.data.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.*;

@NoArgsConstructor
@Data
@Entity
@Table(name = "POSTS")
public class Post {
    @Id
    @Column(name = "ID", nullable = false)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "post")
    @Fetch(FetchMode.SUBSELECT)
    @BatchSize(size = 10)
    private Set<Like> likes = new HashSet<>();
    @Column(name = "TEXT")
    private String text;
    @Enumerated(EnumType.STRING)
    private TypePost typePost;
    @Column(name = "TIMESTAMP")
    private Timestamp timestamp;
    @OneToMany(cascade = CascadeType.ALL)
    @Fetch(FetchMode.SUBSELECT)
    @JoinTable(name = "USER_LIKE_MAPPING",
            joinColumns = {@JoinColumn(name = "POST_ID", referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "LIKE_ID", referencedColumnName = "ID")})
    @MapKeyJoinColumn(name = "USER_ID")
    @BatchSize(size = 10)
    private Map<User, Like> userLikeMap = new HashMap<>();

    @Override
    public int hashCode() {
        return 10;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Post post = (Post) obj;
        return id != null && id.equals(post.getId());
    }
}
