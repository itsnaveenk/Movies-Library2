package com.naveenk.Movies_Library2.controller;

import com.naveenk.Movies_Library2.mappings.AddRemoveMovieFromListReq;
import com.naveenk.Movies_Library2.mappings.CreateListReq;
import com.naveenk.Movies_Library2.service.movieservices.MoviesService;
import com.naveenk.Movies_Library2.service.movieservices.OmdbService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping("/createlist")
    public ResponseEntity<?> createList(@RequestBody CreateListReq createListReq){
        final ResponseEntity<?> list = moviesService.createList(createListReq);

        return list;
    }

    @GetMapping("/mylists")
    public ResponseEntity<?> mylists(){
        final ResponseEntity<?> mylists = moviesService.mylists();
        return mylists;
    }


    @GetMapping("/publiclists")
    public ResponseEntity<?> publiclistsget() {

        return ResponseEntity.ok(String.valueOf('p'));
    }

    @PostMapping("/addmovie")
    public ResponseEntity<?> addMovieToList(@RequestBody AddRemoveMovieFromListReq addRemMovieReq){

        System.out.println(addRemMovieReq.getListName());
        System.out.println(addRemMovieReq.getMovieID());

        return ResponseEntity.ok(String.valueOf('a'));
    }
}
