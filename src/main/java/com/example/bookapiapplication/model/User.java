package com.example.bookapiapplication.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class User implements UserDetails {
    private String username;
    private String password;
    private List<String> roles;

    public User() {}

    public User(String username, String password, List<String> roles) {
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    public void setUsername(String username) { this.username = username; }
    public void setRoles(List<String> roles) { this.roles = roles; }
    public List<String> getRoles() { return roles; }
    public void setPassword(String password) { this.password = password; }

    // --- UserDetails Methods required by Spring Security ---
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream()
                .map(SimpleGrantedAuthority::new) // Converts "ADMIN" to an Authority Spring can read
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() { return password; }
    @Override
    public String getUsername() { return username; }
    @Override
    public boolean isAccountNonExpired() { return true; }
    @Override
    public boolean isAccountNonLocked() { return true; }
    @Override
    public boolean isCredentialsNonExpired() { return true; }
    @Override
    public boolean isEnabled() { return true; }
}