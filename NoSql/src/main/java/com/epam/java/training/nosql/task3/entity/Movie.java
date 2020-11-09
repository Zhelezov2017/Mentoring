package com.epam.java.training.nosql.task3.entity;

import lombok.*;

@EqualsAndHashCode
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Movie {
    private String title;
    private Integer year;
    private String description;
    private String mmpaRating;
    private String dustinRating;
}
