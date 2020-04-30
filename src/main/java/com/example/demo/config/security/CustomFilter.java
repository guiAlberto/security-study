package com.example.demo.config.security;

import java.io.IOException;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class CustomFilter extends UsernamePasswordAuthenticationFilter {

    private static final int TIMEOUT = 3600000;

    private static final SignatureAlgorithm alg = SignatureAlgorithm.HS256;

    private static final String key = "267b8a85-c32e-417c-a6a1-4f8d27339e30";

    public CustomFilter(AuthenticationManager authenticationManager) {
        this.setAuthenticationManager(authenticationManager);
    }
    
    @Override
    protected void successfulAuthentication(HttpServletRequest request,
            HttpServletResponse response, FilterChain chain, Authentication authResult)
            throws IOException, ServletException {
                var user = (CustomUserDetails) authResult.getPrincipal();
                var timeout = System.currentTimeMillis() + TIMEOUT;
                var encodedKey = Base64.getEncoder().encode(key.getBytes());
                
                var token = Jwts
                .builder()
                .setId(UUID.randomUUID().toString())
                .setSubject(user.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(timeout))
                .signWith(alg, encodedKey)
                .compact();

                response.addHeader("token", token);
    }

}
