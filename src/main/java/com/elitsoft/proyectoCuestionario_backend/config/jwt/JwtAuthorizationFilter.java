package com.elitsoft.proyectoCuestionario_backend.config.jwt;


import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        //Se retira el token de los headers http
        String bearerToken = request.getHeader("Authorization");
        //Checkea las credenciales
        if(bearerToken != null && bearerToken.startsWith("Bearer")){
            //String token = bearerToken.replace("Bearer ", "");
            UsernamePasswordAuthenticationToken userPat = TokenUtils.getAuthentication(bearerToken);
            SecurityContextHolder.getContext().setAuthentication(userPat);
        }
        filterChain.doFilter(request,response);
    }
}