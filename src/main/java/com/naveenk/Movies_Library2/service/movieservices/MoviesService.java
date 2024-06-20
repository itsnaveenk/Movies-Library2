package com.naveenk.Movies_Library2.service.movieservices;

import com.naveenk.Movies_Library2.entity.MovieList;
import com.naveenk.Movies_Library2.entity.User;
import com.naveenk.Movies_Library2.mappings.AddRemoveMovieFromListReq;
import com.naveenk.Movies_Library2.mappings.CreateListReq;
import com.naveenk.Movies_Library2.repository.MovieListRepo;
import com.naveenk.Movies_Library2.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MoviesService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private MovieListRepo movieListRepo;


    public ResponseEntity<?> createList(CreateListReq createListReq) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepo.findByUsername(username).orElseThrow(() -> new IllegalArgumentException("User not found"));

        MovieList movieList = new MovieList();
        movieList.setName(createListReq.getName());
        movieList.setIsPublic(createListReq.getType().equals("public"));
        movieList.setUser(user);
        movieListRepo.save(movieList);
        return ResponseEntity.ok(movieList);
    }

    public ResponseEntity<?> mylists() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        final List<MovieList> mylists = movieListRepo.findByUsername(username);

        return ResponseEntity.ok(mylists);
    }


    public ResponseEntity<?> publiclists() {

        final List<MovieList> publiclists = movieListRepo.findByIsPublicTrue();

        return ResponseEntity.ok(publiclists);
    }

    public ResponseEntity<?> addMovieToList(AddRemoveMovieFromListReq addRemMovieReq) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepo.findByUsername(username).orElseThrow(() -> new IllegalArgumentException("User not found"));

        MovieList movieList = movieListRepo.findById(Long.valueOf(addRemMovieReq.getListId())).orElseThrow(() -> new IllegalArgumentException("List not found"));
        movieList.getMovies().add(addRemMovieReq.getMovieId());
        movieListRepo.save(movieList);

        return ResponseEntity.ok(movieList);
    }


    public void deleteMovieFromList(Long listId, String movieId) {
        MovieList movieList = movieListRepo.findById(listId).orElseThrow(() -> new IllegalArgumentException("List not found"));

        movieList.getMovies().remove(movieId);

        movieListRepo.save(movieList);
    }



    public ResponseEntity<?> getMovieList(Long id) {
        MovieList movieList = movieListRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("List not found"));

        return ResponseEntity.ok(movieList);
    }

    public void deleteList(Long listId) {
        movieListRepo.deleteById(listId);
    }
}
