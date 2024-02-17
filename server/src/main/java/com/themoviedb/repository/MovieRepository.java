package com.themoviedb.repository;

import com.themoviedb.entity.Movie;
import com.themoviedb.response.MovieCustomMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    @Query(value = "select id, title, poster_path as posterPath from Movie", nativeQuery = true)
    List<MovieCustomMapping> findMovies();
    @Query(value = "select id from Movie", nativeQuery = true)
    List<Long> findIds();
}
