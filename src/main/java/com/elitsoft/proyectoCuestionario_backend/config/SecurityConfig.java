
package com.elitsoft.proyectoCuestionario_backend.config;


import com.elitsoft.proyectoCuestionario_backend.config.jwt.JwtAuthenticationFilter;
import com.elitsoft.proyectoCuestionario_backend.config.jwt.JwtAuthorizationFilter;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@AllArgsConstructor
public class SecurityConfig {


    private final UserDetailsService userDetailsService;
    private final JwtAuthorizationFilter jwtAuthorizationFilter;
    private final static String ADMIN = "admin";
    private final static String GUEST = "admin_2";
    private final static String REC = "ROLE_REC";
    private final static String ENTR = "ROLE_ENTR";


    @Bean
    protected SecurityFilterChain securityFilterChain(HttpSecurity http,
                                                      AuthenticationManager authManager) throws Exception{
        //Preparación de filtros y de URL objetivo
        JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter();
        jwtAuthenticationFilter.setAuthenticationManager(authManager);
        jwtAuthenticationFilter.setFilterProcessesUrl("/login");

        http.
                csrf().disable()
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests()
                .requestMatchers(new AntPathRequestMatcher("/**/","GET")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/**/","POST")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/**/","PUT")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/**/","DELETE")).permitAll()
                //.requestMatchers(new AntPathRequestMatcher("/country/","GET")).hasAnyAuthority(GUEST)
                //Permitir solamente métodos GETs autorizados con el rol de ADMIN
                //.requestMatchers(HttpMethod.GET,"/**").hasAuthority(ADMIN)
                /*
                .requestMatchers(new AntPathRequestMatcher("/usuarios/","POST")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/usuarios/verificar/**","GET")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/usuarios/verificar/**","POST")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/usuarios/pedir-restauracion-pass","POST")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/**","PUT")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/cargoselitsoft/**","GET")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/paises/**","GET")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/**","DELETE")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/**","DELETE")).hasAnyAuthority(ADMIN,GUEST)
                .requestMatchers(new AntPathRequestMatcher("/categoria/","POST")).hasAnyAuthority(ADMIN,GUEST)
                .requestMatchers(new AntPathRequestMatcher("/categoria/**","GET")).hasAnyAuthority(ADMIN,GUEST)//Todo: Cambiar
                .requestMatchers(new AntPathRequestMatcher("/categoria/actualizar/**","PUT")).hasAnyAuthority(ADMIN,GUEST)
                .requestMatchers(new AntPathRequestMatcher("/categoria/eliminar/**","DELETE")).hasAnyAuthority(ADMIN,GUEST)
                .requestMatchers(new AntPathRequestMatcher("/examen/","POST")).hasAnyAuthority(ADMIN,GUEST)//Todo: Cambiar
                .requestMatchers(new AntPathRequestMatcher("/examen/**","GET")).hasAnyAuthority(ADMIN,GUEST)//Todo: Cambiar
                .requestMatchers(new AntPathRequestMatcher("/examen/actualizar/**","PUT")).hasAnyAuthority(ADMIN)
                .requestMatchers(new AntPathRequestMatcher("/examen/eliminar/**","DELETE")).hasAnyAuthority(ADMIN)
                .requestMatchers(new AntPathRequestMatcher("/pregunta/**","GET")).hasAnyAuthority(ADMIN)
                .requestMatchers(new AntPathRequestMatcher("/pregunta/","POST")).hasAnyAuthority(ADMIN)
                .requestMatchers(new AntPathRequestMatcher("/pregunta/actualizar/**","PUT")).hasAnyAuthority(ADMIN)
                .requestMatchers(new AntPathRequestMatcher("/pregunta/eliminar/**","DELETE")).hasAnyAuthority(ADMIN)
                .requestMatchers(new AntPathRequestMatcher("/usuarios/**","POST")).hasAnyAuthority(ADMIN)
                .requestMatchers(new AntPathRequestMatcher("/usuarios/actualizar/**","PUT")).hasAnyAuthority(ADMIN)
                .requestMatchers(new AntPathRequestMatcher("/usuarios/eliminar/**","DELETE")).hasAnyAuthority(ADMIN)
                .requestMatchers(new AntPathRequestMatcher("/usuarios/**","GET")).hasAnyAuthority(ADMIN)
                .requestMatchers(new AntPathRequestMatcher("/usuarios/","GET")).hasAnyAuthority(GUEST)
                .requestMatchers(new AntPathRequestMatcher("/usuarios/**","GET")).hasAnyAuthority(GUEST)
                .requestMatchers(new AntPathRequestMatcher("/usuarios/","PUT")).hasAnyAuthority(GUEST)
             //   .requestMatchers(new AntPathRequestMatcher("/**","GET")).hasAnyAuthority(ADMIN,GUEST)
                .requestMatchers(new AntPathRequestMatcher("/**","GET")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/**","POST")).hasAnyAuthority(ADMIN,GUEST)
                //.requestMatchers(new AntPathRequestMatcher("/usuarios/","PUT")).hasAnyAuthority(ADMIN,GUEST)

                //.requestMatchers(new AntPathRequestMatcher("/laboral/**","DELETE")).hasAnyAuthority(ADMIN,GUEST)

                */

                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(jwtAuthenticationFilter)
                .addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    protected AuthenticationManager authManager(HttpSecurity http) throws Exception{
        //Definición de authentication manager con UserDetails personalizado
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder())
                .and().build();
    }

    @Bean
    protected PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(12);
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        //Se configura Cors para no permitir que ninguna IP fuera de la aplicación se conecte a la API
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        config.addAllowedOrigin("http://localhost:4200");
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}