package com.damda.ideaconcert.damda.config.Authentication;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Value;

@Value
public class UserAuthentication implements UserDetails {
    private String userId;
    private String userPw;
    private Collection<? extends GrantedAuthority> role;
    
    public UserAuthentication(String userId, String userPw, List<SimpleGrantedAuthority> role) {
        this.userId = userId;
        this.userPw = userPw;
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.role;
    }

    @Override
    public String getPassword() {
        // TODO Auto-generated method stub
        return this.userPw;
    }

    @Override
    public String getUsername() {
        // TODO Auto-generated method stub
        return this.userId;
    }

    @Override
    public boolean isAccountNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isEnabled() {
        // TODO Auto-generated method stub
        return true;
    }
    
}
