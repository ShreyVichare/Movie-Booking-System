package com.example.MovieBookingApplication.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "theater")
public class Theater {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String location;

    private Integer capacity;

    private String screenType;

    @OneToMany(mappedBy = "show", fetch = FetchType.LAZY)
    private List<Show> show;
}
