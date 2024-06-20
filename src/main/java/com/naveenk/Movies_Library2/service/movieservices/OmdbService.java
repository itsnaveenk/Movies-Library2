package com.naveenk.Movies_Library2.service.movieservices;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class OmdbService {

    private String apiKey = "Your_API_Key_Here";

    public ResponseEntity<?> searchMovies(String title) {
        String url = String.format("http://www.omdbapi.com/?apikey=%s&s=%s", apiKey, title);
        RestTemplate restTemplate = new RestTemplate();
        final ResponseEntity<Map> forEntity = restTemplate.getForEntity(url, Map.class);
        System.out.println(forEntity);
        return forEntity;
    }

    public ResponseEntity<?> searchById(String id) {
        String url = String.format("http://www.omdbapi.com/?apikey=%s&i=%s", apiKey, id);
        RestTemplate restTemplate = new RestTemplate();
        final ResponseEntity<Map> forEntity = restTemplate.getForEntity(url, Map.class);
        System.out.println(forEntity);
        return forEntity;
    }
}
