package com.themoviedb.controller;


import com.themoviedb.service.SynchronizeActorsService;
import com.themoviedb.service.SynchronizeImagesService;
import com.themoviedb.service.SynchronizeMoviesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("admin")
public class AdminController {
    @Autowired
    private SynchronizeMoviesService synchronizeMoviesService;
    @Autowired
    private SynchronizeActorsService synchronizeActorsService;
    @Autowired
    private SynchronizeImagesService synchronizeImagesService;

    @GetMapping("synchronizeMovies")
    public ResponseEntity synchronizeMovies() {
        synchronizeMoviesService.insertMoviesFromApi();
        return ResponseEntity.ok("Movies added to database");
    }

    @GetMapping("synchronizeActors")
    public ResponseEntity synchronizeActors() {
        synchronizeActorsService.insertActorsFromApi();
        return ResponseEntity.ok("Actors added to database");
    }

    @GetMapping("synchronizeImages")
    public ResponseEntity synchronizeImages() {
        synchronizeImagesService.insertImagesFromApi();
        return ResponseEntity.ok("Images added to database");
    }
}
