package com.naveenk.Movies_Library2.service.movieservices;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class OmdbService {

    @Value("${API}")
    private String apiKey;

    public ResponseEntity<?> searchMovies(String title) {
        final ResponseEntity<Map> forEntity;
        try {
            String url = String.format("http://www.omdbapi.com/?apikey=%s&s=%s", apiKey, title);
            RestTemplate restTemplate = new RestTemplate();
            forEntity = restTemplate.getForEntity(url, Map.class);
            System.out.println(forEntity);
        } catch (RestClientException e) {
            throw new RuntimeException(e);
        }
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
