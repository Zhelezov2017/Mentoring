package com.epam.java.training.spring_boot.task5_6.data.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "AGENTS")
public class Agent {
    @Id
    @Column(name = "ID", nullable = false)
    private Long id;
    @Column(name = "FIRST_NAME")
    private String firstName;
    @Column(name = "LAST_NAME")
    private String lastName;
    @Column(name = "AGENT_LEVEL")
    private Long agentLevel;
    @OneToMany(fetch = FetchType.EAGER)
    private List<RealEstate> realEstate = new ArrayList<>();

    @Override
    public String toString() {
        return "Agent{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", agentLevel=" + agentLevel +
                '}';
    }
}
