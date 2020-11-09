package com.epam.java.training.hibernate.task1.data.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

import javax.persistence.*;

@NoArgsConstructor
@Data
@EqualsAndHashCode
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@NamedQueries({
        @NamedQuery(name = "Group_findByGroupId", query = "FROM Group WHERE id = :groupId"),
        @NamedQuery(name = "Group_findAll", query = "FROM Group")})
@Table(name = "GROUPS")
public class Group {
    @Id
    @Column(name = "ID", nullable = false)
    private Long id;
    @Column(name = "GROUP_NAME")
    private String groupName;
    @Embedded
    private ContactUser contactUser;
}

