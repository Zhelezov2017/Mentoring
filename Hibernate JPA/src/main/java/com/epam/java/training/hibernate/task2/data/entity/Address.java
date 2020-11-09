package com.epam.java.training.hibernate.task2.data.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Data
@Embeddable
public class Address {
    private String street;
    private String house;
    private Long flat;
}
