package com.naveenk.Movies_Library2.repository;

import com.naveenk.Movies_Library2.entity.MovieList;
import com.naveenk.Movies_Library2.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieListRepo extends JpaRepository<MovieList, Long> {
    List<MovieList> findByUser(User user);
    List<MovieList> findByIsPublicTrue();
}
