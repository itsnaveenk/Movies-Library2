package com.naveenk.Movies_Library2.service;

import com.naveenk.Movies_Library2.entity.User;
import com.naveenk.Movies_Library2.mappings.*;
import com.naveenk.Movies_Library2.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class UserAuthenticationService {
    @Autowired
    UserRepo userRepo;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtServiceImpl jwtServiceImpl;

    public User register(RegisterDetails registerDetails) {
        User user = new User();
        user.setUsername(registerDetails.getUsername());
        user.setEmail(registerDetails.getEmail());
        user.setPassword(bCryptPasswordEncoder.encode(registerDetails.getPassword()));

        return userRepo.save(user);
    }

    public LoginDetailsRes login(LoginDetailsReq loginDetailsReq){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDetailsReq.getUsername(),loginDetailsReq.getPassword()));
        var user = userRepo.findByUsername(loginDetailsReq.getUsername()).orElseThrow(()-> new IllegalArgumentException("invalid username"));

        var jwt = jwtServiceImpl.generateToken(user);
        var refreshToken = jwtServiceImpl.generateRefreshToken(new HashMap<>(), user);

        LoginDetailsRes loginDetailsRes = new LoginDetailsRes();
        loginDetailsRes.setToken(jwt);
        loginDetailsRes.setRefreshToken(refreshToken);

        return loginDetailsRes;
    }

    public LoginDetailsRes refreshToken(RefreshTokenReq refreshTokenReq) {

        String username = jwtServiceImpl.extractUsername(refreshTokenReq.getToken());
        User user = userRepo.findByUsername(username).orElseThrow();
        if(jwtServiceImpl.isTokenValid(refreshTokenReq.getToken(), user)){
            var jwt = jwtServiceImpl.generateToken(user);
            var refreshToken = jwtServiceImpl.generateRefreshToken(new HashMap<>(), user);

            LoginDetailsRes loginDetailsRes = new LoginDetailsRes();
            loginDetailsRes.setToken(jwt);
            loginDetailsRes.setRefreshToken(refreshToken);

            return loginDetailsRes;

        }
        return null;
    }
}
