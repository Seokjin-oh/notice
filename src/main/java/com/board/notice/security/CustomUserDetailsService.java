package com.board.notice.security;

import com.board.notice.mapper.UserMapper;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * The type Custom user details service.
 */
@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String userId)
            throws UsernameNotFoundException {
        Map<String, Object> userMap = Optional.ofNullable(userMapper.findById(userId))
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found with id : " + userId)
        );

        List<GrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority(userMap.get("ROLE").toString()));

        return new CustomUser((int)userMap.get("USER_KEY"), userMap.get("USER_ID").toString(), userMap.get("USER_PW").toString(), authorities);
    }
}