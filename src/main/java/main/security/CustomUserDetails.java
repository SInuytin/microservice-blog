package main.security;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import main.users.model.Role;
import main.users.model.User;

public record CustomUserDetails(User user) implements UserDetails {

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.name()))
                .collect(Collectors.toSet());
    }


    public Long getId() {
        return user.getId();
    }

    public String getEmail() {
        return user.getEmail();
    }

    public Set<Role> getRole() {
        return user.getRoles();
    }

    @Override
    public String getPassword() {
        return user.getPasswordHash();
    }

    @Override
    public String getUsername() {
        return user.getName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // TODO stub
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // TODO stub
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // TODO stub
    }

    @Override
    public boolean isEnabled() {
        return true; // TODO stub
    }

}
