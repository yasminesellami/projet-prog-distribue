package com.themoviedb.repository;

import com.themoviedb.entity.Actor;
import com.themoviedb.response.ActorCustomMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface ActorRepository extends JpaRepository<Actor, Long> {
    @Query(value = "select name,character,profile_path profilePath from actor where movie_id=?1 LIMIT 10", nativeQuery = true)
    List<ActorCustomMapping> getActorByMovie(Long id);
}