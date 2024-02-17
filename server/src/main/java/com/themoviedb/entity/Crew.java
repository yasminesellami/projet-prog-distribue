package com.themoviedb.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Crew {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String profilePath;
    private String job;
    @ManyToOne
    @JoinColumn(name = "movie_id")
    @JsonIgnoreProperties("actors")
    private Movie movie;
}