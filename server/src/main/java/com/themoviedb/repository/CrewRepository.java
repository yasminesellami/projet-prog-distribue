package com.themoviedb.repository;

import com.themoviedb.entity.Crew;
import com.themoviedb.response.CrewCustomMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CrewRepository extends JpaRepository<Crew, Long> {
    @Query(value = "select name,job,profile_path profilePath from crew where movie_id=?1 AND (job='Director' OR job='Original Music Composer')", nativeQuery = true)
    List<CrewCustomMapping> getCrewByMovie(Long id);
}
