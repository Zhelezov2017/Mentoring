package com.epam.java.training.hibernate.task1.data.entity;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.*;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;


@NoArgsConstructor
@Data
@Entity
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "USERS")
public class User {
    @Id
    @Column(name = "ID", nullable = false)
    private Long id;
    @Basic
    @Column(name = "FIRST_NAME", nullable = false, length = 100)
    private String firstName;
    @Basic
    @Column(name = "LAST_NAME", nullable = false, length = 50)
    private String lastName;
    @Column(name = "BIRTH_DATE")
    private LocalDate birthDate;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    @Fetch(FetchMode.SUBSELECT)
    @BatchSize(size = 10)
    private Set<Post> posts = new HashSet<>();
    @Version
    @Column(name = "VERSION", nullable = false)
    private int version;

    @Override
    public int hashCode() {
        return 5;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        User user = (User) obj;
        return id != null && id.equals(user.getId());
    }

}
