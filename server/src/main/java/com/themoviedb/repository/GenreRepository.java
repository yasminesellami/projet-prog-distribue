package com.themoviedb.repository;

import com.themoviedb.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GenreRepository extends JpaRepository<Genre, Long> {
    @Query(value = "select * from Genre", nativeQuery = true)
    List<Genre> getGenresById(Long id);
}
