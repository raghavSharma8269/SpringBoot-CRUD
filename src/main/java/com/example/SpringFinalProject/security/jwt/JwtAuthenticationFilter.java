package com.example.SpringFinalProject.security.jwt;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String token = null;
        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);
        }

        if (token != null && JwtUtil.isTokenValid(token)) {
            Claims claims = JwtUtil.getClaims(token);
            String username = claims.getSubject();
            String authority = JwtUtil.getAuthorityFromToken(token);

            List<SimpleGrantedAuthority> authorities = new ArrayList<>();
            if ("ADMIN".equals(authority)) {
                // If user is ADMIN, give them both ADMIN and USER authorities
                authorities.add(new SimpleGrantedAuthority("ADMIN"));
                authorities.add(new SimpleGrantedAuthority("USER"));
                System.out.println("Adding authority: " + authority);

            } else if ("USER".equals(authority)) {
                // If user is USER, give them only USER authority
                authorities.add(new SimpleGrantedAuthority("USER"));
                System.out.println("Adding authority: " + authority);

            }

            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    username,
                    null,
                    authorities
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);

        }

        filterChain.doFilter(request, response);

    }
}

