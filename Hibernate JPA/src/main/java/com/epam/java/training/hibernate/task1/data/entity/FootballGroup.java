package com.epam.java.training.hibernate.task1.data.entity;

import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@EqualsAndHashCode(callSuper = false)
@PrimaryKeyJoinColumn(name = "FOOTBALL_GROUP_ID")
@Table(name = "FOOTBALL_GROUPS")
public class FootballGroup extends Group {
    @Column(name = "FOOTBALL_SKILL")
    private Long footballSkill;
}
