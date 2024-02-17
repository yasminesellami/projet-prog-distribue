package com.themoviedb.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.themoviedb.entity.Backdrop;
import com.themoviedb.repository.ImageRepository;
import com.themoviedb.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class SynchronizeImagesService {
    private ImageRepository imageRepository;
    private MovieRepository movieRepository;
    @Value("${api.url}")
    private String imagesApi;
    @Value("${api.key}")
    private String keyApi;

    @Autowired
    public SynchronizeImagesService(MovieRepository movieRepository, ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
        this.movieRepository = movieRepository;
    }

    public void insertImagesFromApi() {
        List<Long> idMovies = movieRepository.findIds();
        RestTemplate restTemplate = new RestTemplate();
        idMovies.forEach((id) -> {
            String imagesUrl = imagesApi + "/" + id + "/images?api_key=" + keyApi;
            String jsonString = restTemplate.getForObject(imagesUrl, String.class);
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            try {
                JsonNode jsonNode = objectMapper.readTree(jsonString);
                JsonNode resultsNode = jsonNode.get("backdrops");

                for (JsonNode ImagesNode : resultsNode) {
                    Backdrop backdrop = new Backdrop();
                    if (ImagesNode.get("iso_639_1").isNull()) {
                        backdrop.setFilePath(ImagesNode.get("file_path").asText());
                        backdrop.setMovie(movieRepository.findById(id).get());
                        imageRepository.save(backdrop);
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        });


    }


}
