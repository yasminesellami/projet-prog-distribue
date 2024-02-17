package com.themoviedb.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.themoviedb.entity.Actor;
import com.themoviedb.entity.Crew;
import com.themoviedb.repository.ActorRepository;
import com.themoviedb.repository.CrewRepository;
import com.themoviedb.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class SynchronizeActorsService {

    private ActorRepository actorRepository;
    private MovieRepository movieRepository;
    private CrewRepository crewRepository;
    @Value("${api.url}")
    private String actorsApi;
    @Value("${api.key}")
    private String keyApi;

    @Autowired
    public SynchronizeActorsService(ActorRepository actorRepository, MovieRepository movieRepository, CrewRepository crewRepository) {
        this.actorRepository = actorRepository;
        this.movieRepository = movieRepository;
        this.crewRepository = crewRepository;
    }


    public void insertActorsFromApi() {
        List<Long> idMovies = movieRepository.findIds();
        RestTemplate restTemplate = new RestTemplate();
        idMovies.forEach((id) -> {
            String actorsUrl = actorsApi + "/" + id + "/credits?api_key=" + keyApi;
            String jsonString = restTemplate.getForObject(actorsUrl, String.class);
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            try {
                JsonNode jsonNode = objectMapper.readTree(jsonString);
                JsonNode resultsNode = jsonNode.get("cast");

                for (JsonNode ActorNode : resultsNode) {
                    Actor actor = new Actor();

                    actor.setId(ActorNode.get("id").asLong());
                    actor.setName(ActorNode.get("name").asText());
                    actor.setProfilePath(ActorNode.get("profile_path").asText());
                    actor.setCharacter(ActorNode.get("character").asText());
                    actor.setMovie(movieRepository.findById(id).get());

                    actorRepository.save(actor);
                }
                resultsNode = jsonNode.get("crew");
                for (JsonNode ActorNode : resultsNode) {
                    Crew crew = new Crew();
                    crew.setName(ActorNode.get("name").asText());
                    crew.setProfilePath(ActorNode.get("profile_path").asText());
                    crew.setJob(ActorNode.get("job").asText());
                    crew.setMovie(movieRepository.findById(id).get());

                    crewRepository.save(crew);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        });


    }
}
