package com.themoviedb.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.themoviedb.entity.Movie;
import com.themoviedb.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class SynchronizeService {
    private MovieRepository movieRepository;

    @Autowired
    public SynchronizeService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public void insertMoviesFromApi() {
        String apiUrl = "https://api.themoviedb.org/3/movie/now_playing?api_key=1f39c937c2cfbeeb721a86724edb0283";
        RestTemplate restTemplate = new RestTemplate();
        String jsonString = restTemplate.getForObject(apiUrl, String.class);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        try {
            JsonNode jsonNode = objectMapper.readTree(jsonString);
            JsonNode resultsNode = jsonNode.get("results");

            for (JsonNode movieNode : resultsNode) {
                Movie movie = new Movie();

                movie.setId(movieNode.get("id").asLong());
                movie.setTitle(movieNode.get("title").asText());
                movie.setPosterPath(movieNode.get("poster_path").asText());
                movie.setOverview(movieNode.get("overview").asText());
                movie.setReleaseDate(movieNode.get("release_date").asText());
                movie.setPopularity(movieNode.get("popularity").asDouble());
                movie.setOriginalLanguage(movieNode.get("original_language").asText());

                movieRepository.save(movie);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
