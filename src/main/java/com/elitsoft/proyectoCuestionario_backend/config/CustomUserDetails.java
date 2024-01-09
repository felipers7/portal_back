package com.elitsoft.proyectoCuestionario_backend.config;

import com.elitsoft.proyectoCuestionario_backend.entities.User;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@AllArgsConstructor
public class CustomUserDetails implements UserDetails {
    //Se hace override de métodos teniendo en cuenta  los roles de usuario
    private final User appuser;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return appuser.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return appuser.getPassword();
    }

    @Override
    public String getUsername() {
        return appuser.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
