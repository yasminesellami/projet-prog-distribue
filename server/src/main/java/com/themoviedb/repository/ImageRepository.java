package com.themoviedb.repository;

import com.themoviedb.entity.Backdrop;
import com.themoviedb.response.ImageCustomMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ImageRepository extends JpaRepository<Backdrop, Long> {
    @Query(value = "select file_path filePath from backdrop where movie_id=?1", nativeQuery = true)
    List<ImageCustomMapping> getImagesByMovie(Long id);
}
