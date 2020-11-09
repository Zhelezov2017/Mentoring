package com.epam.java.training.hibernate.task1.data.entity;

import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = false)
@Entity
@PrimaryKeyJoinColumn(name = "VOLLEYBALL_GROUP_ID")
@Table(name = "VOLLEYBALL_GROUPS")
public class VolleyballGroup extends Group {
    @Column(name = "VOLLEYBALL_SKILL")
    private Long volleyballSkill;
}
