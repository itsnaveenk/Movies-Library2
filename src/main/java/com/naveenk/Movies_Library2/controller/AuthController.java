package com.naveenk.Movies_Library2.controller;

import com.naveenk.Movies_Library2.entity.User;
import com.naveenk.Movies_Library2.mappings.LoginDetailsReq;
import com.naveenk.Movies_Library2.mappings.LoginDetailsRes;
import com.naveenk.Movies_Library2.mappings.RefreshTokenReq;
import com.naveenk.Movies_Library2.mappings.RegisterDetails;
import com.naveenk.Movies_Library2.service.UserAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    UserAuthenticationService userAuthenticationService;
    @Autowired
    private LoginDetailsReq loginDetailsReq;

    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

    // register user is working fine...
    @PostMapping("/signup")
    public ResponseEntity<User> signUpPost(@RequestBody RegisterDetails registerDetails){
        return ResponseEntity.ok(userAuthenticationService.register(registerDetails));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginDetailsRes> loginPost(@RequestBody LoginDetailsReq loginDetailsReq){
        final LoginDetailsRes login = userAuthenticationService.login(loginDetailsReq);
        return ResponseEntity.ok(login);
    }
    @PostMapping("/refreshtoken")
    public ResponseEntity<LoginDetailsRes> refresh(@RequestBody RefreshTokenReq refreshTokenReq){

        final ResponseEntity<LoginDetailsRes> ok = ResponseEntity.ok(userAuthenticationService.refreshToken(refreshTokenReq));
        return ok;
    }

}
