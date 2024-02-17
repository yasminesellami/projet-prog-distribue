package com.themoviedb.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Actor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String character;
    private String profilePath;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    @JsonIgnoreProperties("actors")

    private Movie movie;

}
