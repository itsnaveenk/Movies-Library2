package com.naveenk.Movies_Library2.repository;

import com.naveenk.Movies_Library2.entity.MovieList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MovieListRepo extends JpaRepository<MovieList, Long> {
    @Query("SELECT m FROM MovieList m WHERE m.user.username = :username")
    List<MovieList> findByUsername(@Param("username") String username);
    List<MovieList> findByIsPublicTrue();
}
