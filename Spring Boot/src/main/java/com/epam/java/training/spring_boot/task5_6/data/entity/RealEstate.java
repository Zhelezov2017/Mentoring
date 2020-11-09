package com.epam.java.training.spring_boot.task5_6.data.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@EqualsAndHashCode
@NoArgsConstructor
@Getter
@Setter
@Entity
@ToString
@Table(name = "REAL_ESTATES")
public class RealEstate {
    @Id
    @Column(name = "ID", nullable = false)
    private Long id;
    @Column(name = "NAME")
    private String name;
    @ManyToOne
    @JoinColumn(name = "AGENT_ID")
    private Agent agent;
    @Column(name = "PRICE")
    private Double price;
    @Column(name = "NUMBER_OF_VIEWS")
    private Long countOfViews = 0L;
    @Column(name = "NUMBER_OF_ROOM")
    private Long numberOfRoom;
    @NonNull
    @Column(name = "SOLD")
    private Boolean sold;
    @Column(name = "SOLD_DATE")
    private LocalDate soldDate;

}
