package com.naveenk.Movies_Library2.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {


    @GetMapping("/publiclists")
    public String publiclistsget() {


        return "public lists";
    }
}
