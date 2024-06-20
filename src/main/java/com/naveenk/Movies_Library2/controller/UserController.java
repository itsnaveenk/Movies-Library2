package com.naveenk.Movies_Library2.controller;

import com.naveenk.Movies_Library2.mappings.ListInfo;
import com.naveenk.Movies_Library2.repository.MovieListRepo;
import com.naveenk.Movies_Library2.service.JwtServiceImpl;
import com.naveenk.Movies_Library2.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    private final JwtServiceImpl jwtServiceImpl;

    @Autowired
    private MovieListRepo movieListRepo;

    public UserController(JwtServiceImpl jwtServiceImpl) {
        this.jwtServiceImpl = jwtServiceImpl;
    }

    @GetMapping("/hello")
    public String hello() {

        return "hello";
    }

    @PostMapping("/fetchusername")
    public ResponseEntity<Map<String, String>> fetchUserName(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7); // Extract the token
            Map<String, String> response = new HashMap<>();
            response.put("username", jwtServiceImpl.extractUsername(token));
            return ResponseEntity.ok(response);
        }

        return null;
    }

    //
//    @GetMapping("/lists")
//    public ResponseEntity<List<ListInfo>> getLists() {
//        List<ListInfo> lists = movieListRepo.findAll().stream()
//                .map(movieList -> new ListInfo(movieList.getId(), movieList.getName()))
//                .collect(Collectors.toList());
//        return ResponseEntity.ok(lists);
//    }
    @GetMapping("/lists")
    public ResponseEntity<List<ListInfo>> getLists() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        List<ListInfo> lists = movieListRepo.findByUsername(username).stream()
                .map(movieList -> new ListInfo(movieList.getId(), movieList.getName()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(lists);
    }

}
