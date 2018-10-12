package com.forest10.spring.boot.family.jwt.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.forest10.spring.boot.family.common.domain.UserAuth;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

/**
 * @author forest10
 */
public class JWTLoginFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;

    public JWTLoginFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)
        throws AuthenticationException {
        try {
            //		String username = obtainUsername(req);
            //		String password = obtainPassword(req);
            UserAuth user = new ObjectMapper().readValue(req.getInputStream(), UserAuth.class);

            return authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), new ArrayList<>()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain,
        Authentication auth) {

        String token = Jwts.builder()
            .setSubject(((User)auth.getPrincipal()).getUsername())
            .setExpiration(new Date(System.currentTimeMillis() + 60 * 60 * 24 * 1000))
            .signWith(SignatureAlgorithm.HS512, "MyJwtSecret")
            .compact();
        res.addHeader("Authorization", "Bearer " + token);
    }
}
