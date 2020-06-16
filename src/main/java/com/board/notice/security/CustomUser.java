package com.board.notice.security;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * The type Custom user.
 *
 * @author Seok Jin, Oh
 * @since 2020 -06-10
 */
public class CustomUser implements UserDetails {
    private int userKey;
    private String username;
    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    /**
     * Instantiates a new Custom user.
     *
     * @param userKey     the user key
     * @param username    the username
     * @param password    the password
     * @param authorities the authorities
     */
    public CustomUser(int userKey, String username, String password, Collection<? extends GrantedAuthority> authorities) {
        this.userKey = userKey;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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

    /**
     * Gets user key.
     *
     * @return the user key
     */
    public int getUserKey() {
        return userKey;
    }
}
