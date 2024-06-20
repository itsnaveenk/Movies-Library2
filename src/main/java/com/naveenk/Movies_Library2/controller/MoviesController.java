package com.naveenk.Movies_Library2.controller;

import com.naveenk.Movies_Library2.mappings.AddRemoveMovieFromListReq;
import com.naveenk.Movies_Library2.mappings.CreateListReq;
import com.naveenk.Movies_Library2.service.movieservices.MoviesService;
import com.naveenk.Movies_Library2.service.movieservices.OmdbService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movies")
public class MoviesController {

    @Autowired
    OmdbService omdbService;

    @Autowired
    MoviesService moviesService;

    @PostMapping("/search/{title}")
    public ResponseEntity<?> searchMovies(@PathVariable String title){
        ResponseEntity<?> response = omdbService.searchMovies(title);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/searchById/{id}")
    public ResponseEntity<?> searchById(@PathVariable String id){
        ResponseEntity<?> response = omdbService.searchById(id);
        return ResponseEntity.ok(response);
    }


    @PostMapping("/createlist")
    public ResponseEntity<?> createList(@RequestBody CreateListReq createListReq){
        final ResponseEntity<?> list = moviesService.createList(createListReq);

        return list;
    }
    @Transactional
    @DeleteMapping("/deletelist/{listId}")
    public ResponseEntity<?> deleteList(@PathVariable Long listId) {
        try {
            moviesService.deleteList(listId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete list.");
        }
    }
    @Transactional
    @DeleteMapping("/deletemovie/{listId}/{movieId}")
    public ResponseEntity<?> deleteMovieFromList(@PathVariable Long listId, @PathVariable String movieId) {
        try {
            moviesService.deleteMovieFromList(listId, movieId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete movie from list.");
        }
    }

    @GetMapping("/mylists")
    public ResponseEntity<?> mylists(){
        final ResponseEntity<?> mylists = moviesService.mylists();
        return mylists;
    }


    @GetMapping("/publiclists")
    public ResponseEntity<?> publiclistsget() {
        final ResponseEntity<?> publiclists = moviesService.publiclists();
        return publiclists;
    }

    @PostMapping("/addmovie")
    public ResponseEntity<?> addMovieToList(@RequestBody AddRemoveMovieFromListReq addRemMovieReq){

        final ResponseEntity<?> response = moviesService.addMovieToList(addRemMovieReq);

        return response;
    }

    @GetMapping("/list/{id}")
    public ResponseEntity<?> getMovieList(@PathVariable Long id){
        final ResponseEntity<?> response = moviesService.getMovieList(id);
        return response;
    }
}
