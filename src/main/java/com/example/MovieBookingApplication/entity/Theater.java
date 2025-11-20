package com.example.MovieBookingApplication.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "theater")
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Theater {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String location;

    private Integer capacity;

    private String screenType;

    @OneToMany(mappedBy = "theater", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Show> show;
}
