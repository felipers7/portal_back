package com.elitsoft.proyectoCuestionario_backend.config.jwt;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TokenUtils {

    //Secret de prueba
    private final static String TOKEN_SECRET = "L4Ia1+thkagsOpyqJJavMAu48RR-z#1KBeNNz2FDS+mSxzJ1clCH3$gXoJP1%NGZPrHo5a%-%diOWnIx$+jyQpLDiGO&rJ7M%OIGgFQF5_PjqKUIULxiXUnDB9ETmk61q%jAi9Kj-SGPpruhwZvFJUN2M9";
    private final static long ACCESS_TOKEN_VALIDITY_SECONDS = 2_592_000L;

    public static String createToken(String username, Collection<? extends GrantedAuthority> roles){
        //Se le otorga tiempo de vida determinado
        long expirationTime = ACCESS_TOKEN_VALIDITY_SECONDS * 1_000;
        Date expirationDate = new Date(System.currentTimeMillis() + expirationTime);

        //Se extraen los roles de granted authority
        List<String> newRoles = roles.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());

        System.out.println(newRoles);

        //Se construye el JWT añadiendo los roles
        return Jwts.builder()
                .setSubject(username)
                .setExpiration(expirationDate)
                .claim("authorities",newRoles)
                .signWith(Keys.hmacShaKeyFor(TOKEN_SECRET.getBytes()))
                .compact();
    }

    public static UsernamePasswordAuthenticationToken getAuthentication(String token){

        token = token.replace("Bearer ", "");
        try{
            //Se extraen las claims halladas en el token
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(TOKEN_SECRET.getBytes())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            //Se extrae el username y el rol
            String username = claims.getSubject();
            //String role = claims.get("roles", String.class);
            List<String> authorities = claims.get("authorities", List.class);


            authorities.forEach(a ->{
                System.out.println("role "+ a);
            });

             Collection<? extends GrantedAuthority> roles = authorities.stream()
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());



            return new UsernamePasswordAuthenticationToken(username,null,roles);
        }
        catch (JwtException e){
            System.out.println(e);
            return null;
        }
    }


}
