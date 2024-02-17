package com.themoviedb.controller;

import com.themoviedb.entity.Genre;
import com.themoviedb.entity.Movie;
import com.themoviedb.response.ActorCustomMapping;
import com.themoviedb.response.CrewCustomMapping;
import com.themoviedb.response.ImageCustomMapping;
import com.themoviedb.response.MovieCustomMapping;
import com.themoviedb.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("movie-list-vite")
public class HomeController {
    @Autowired
    private HomeService homeService;

    @GetMapping("movie/{id}")
    public ResponseEntity getListActorsByMovie(@PathVariable Long id) {
        List<Genre> genres = homeService.getListGenres(id);
        Movie movie = homeService.getMovieService(id);
        List<ActorCustomMapping> actors = homeService.getListActorService(id);
        List<CrewCustomMapping> crews = homeService.getListCrewService(id);
        List<ImageCustomMapping> images = homeService.getListImageService(id);

        if (actors.isEmpty()) {
            return new ResponseEntity<>(new CustomResponse("No data found"), HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(new CustomResponseDetails(movie, actors, crews, images));
    }

    @GetMapping("/")
    public ResponseEntity<CustomResponse> getListMovie() {
        List<MovieCustomMapping> movies = homeService.getListMovieService();

        if (movies.isEmpty()) {
            return new ResponseEntity<>(new CustomResponse("No data found"), HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(new CustomResponse(movies));
    }
    record CustomResponse(Object results) {
    }

    record CustomResponseDetails(Object movie, Object cast, Object crew, Object images) {

    }


}
