package com.naveenk.Movies_Library2.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.function.Function;

@Service
public class JwtServiceImpl {

    private final String secretKey = "f1e2c3d4b5a6e7f8091121314151617181920a1b2c3d4e5f67890abcdef123456";

    public String generateToken(UserDetails userDetails){
        String token = Jwts.builder().setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000L * 60 * 60 * 24 * 90))
                .signWith(getSigInKey(), SignatureAlgorithm.HS256)
                .compact();

        return token;
    }

    public String extractUsername(String token){
        return extractClaims(token, Claims::getSubject);
    }

    private <T> T extractClaims(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);

    }

    private Claims extractAllClaims(String token) {
        Claims claim = Jwts.parser().setSigningKey(getSigInKey()).parseClaimsJws(token).getBody();
        return claim;
    }

    private Key getSigInKey() {
        byte[] key = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(key);
    }

    public boolean isTokenValid(String token, UserDetails userDetails){
        final String username = extractUsername(token);
        final boolean b = username.equals(userDetails.getUsername()) && !isTokenExpired(token);
        return b;
    }

    private boolean isTokenExpired(String token) {

        final boolean before = extractClaims(token, Claims::getExpiration).before(new Date());
        return before;
    }


    public String generateRefreshToken(HashMap<String,Object> extactClaims, UserDetails userDetails){
        String token = Jwts.builder().setClaims(extactClaims).setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000L * 60 * 60 * 24 * 180))
                .signWith(getSigInKey(), SignatureAlgorithm.HS256)
                .compact();

        return token;
    }


}
