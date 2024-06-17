package com.naveenk.Movies_Library2.service.movieservices;

import com.naveenk.Movies_Library2.entity.MovieList;
import com.naveenk.Movies_Library2.entity.User;
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

    public ResponseEntity<?> mylists(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        final List<MovieList> mylists = movieListRepo.findByUsername(username);

        return ResponseEntity.ok(mylists);
    }


}
