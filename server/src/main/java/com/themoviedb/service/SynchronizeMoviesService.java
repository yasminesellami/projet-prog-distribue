package com.themoviedb.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.themoviedb.entity.Genre;
import com.themoviedb.entity.Movie;
import com.themoviedb.repository.GenreRepository;
import com.themoviedb.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Component
public class SynchronizeMoviesService {

    private MovieRepository movieRepository;
    @Autowired
    private GenreRepository genreRepository;
    @Value("${api.url}" + "/now_playing?" + "api_key=" + "${api.key}")
    private String nowPlayingApi;
    @Value("https://api.themoviedb.org/3" + "/genre/movie/list?" + "api_key=" + "${api.key}")
    private String genresApi;

    @Autowired
    public SynchronizeMoviesService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public void insertMoviesFromApi() {
        RestTemplate restTemplate = new RestTemplate();
        String jsonString = restTemplate.getForObject(genresApi, String.class);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        try {
            JsonNode jsonNode = objectMapper.readTree(jsonString);
            JsonNode resultsNode = jsonNode.get("genres");

            for (JsonNode movieNode : resultsNode) {
                Genre genre = new Genre();
                genre.setId(movieNode.get("id").asLong());
                genre.setName(movieNode.get("name").asText());

                genreRepository.save(genre);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


        jsonString = restTemplate.getForObject(nowPlayingApi, String.class);
        objectMapper = new ObjectMapper();
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
                LocalDate date = LocalDate.parse(movieNode.get("release_date").asText());
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yyyy", Locale.FRENCH);
                String formattedDate = date.format(formatter);
                movie.setReleaseDate(formattedDate);
                movie.setPopularity(movieNode.get("popularity").asDouble());
                movie.setOriginalLanguage(movieNode.get("original_language").asText());
                movie.setBackdropPath(movieNode.get("backdrop_path").asText());

                JsonNode genreIdsNode = movieNode.get("genre_ids");
                if (genreIdsNode != null && genreIdsNode.isArray()) {
                    for (JsonNode genreIdNode : genreIdsNode) {
                        Long genreId = genreIdNode.asLong();
                        Genre genre = genreRepository.findById(genreId).orElse(null);
                        if (genre != null) {
                            movie.getGenres().add(genre);
                        }
                    }
                }

                movieRepository.save(movie);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
