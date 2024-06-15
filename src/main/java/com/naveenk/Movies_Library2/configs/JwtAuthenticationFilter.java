package com.naveenk.Movies_Library2.configs;

import com.naveenk.Movies_Library2.service.UserService;
import com.naveenk.Movies_Library2.service.JwtServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private JwtServiceImpl jwtService;
    @Autowired
    private UserService userService;
    @Autowired
    private JwtServiceImpl jwtServiceImpl;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String username;
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }
        jwt = authHeader.substring(7);
        username = jwtService.extractUsername(jwt);
         if (username != null && SecurityContextHolder.getContext().getAuthentication()==null){
             UserDetails userDetails = userService.userDetailsService().loadUserByUsername(username);
             if(jwtServiceImpl.isTokenValid(jwt, userDetails)){
                 SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
                 UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                         userDetails, null,userDetails.getAuthorities()
                 );
                 token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                 securityContext.setAuthentication(token);
                 SecurityContextHolder.setContext(securityContext);
             }
         }
         filterChain.doFilter(request, response);
    }
}
