package com.practica.profesionalizante.auth.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import com.practica.profesionalizante.auth.service.impl.UserDetailsServiceImpl;

public class JwtTokenFilter extends OncePerRequestFilter {
    private final static Logger logger = LoggerFactory.getLogger(JwtTokenFilter.class);

    @Autowired
    JwtProvider jwtProvider;

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        try{
            String token = getToken(request);
            if(token != null && jwtProvider.validateToken(token)){
                String userName = jwtProvider.getNameUserFormToken(token);
                UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
                UsernamePasswordAuthenticationToken auth =
                        new UsernamePasswordAuthenticationToken(userDetails,
                                null,
                                userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
            filterChain.doFilter(request, response);


        }catch (Exception e){
            System.out.println(e.getCause() + e.getLocalizedMessage());
            logger.error("Falla en el metodo do Filter");
        }
    }

    private String getToken(HttpServletRequest request){
        String header = request.getHeader("Authorization");
        //Si el header de la peticion tiene Authorization
        //entonces me fijo si esta existe y si comienza con bearer (token)
        if (header != null && header.startsWith("Bearer")) {
            return header.replace("Bearer", ""); // Quito el bearer
        }
        return null;
    }
}
