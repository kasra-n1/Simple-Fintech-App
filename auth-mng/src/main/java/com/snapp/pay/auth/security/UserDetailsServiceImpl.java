package com.snapp.pay.auth.security;

import com.snapp.pay.auth.model.User;
import com.snapp.pay.auth.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;


@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserService userService;

    @Override
    public JwtUser loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userService.findByUsername(username);
        if (user != null) {
            return new JwtUser(user.getUsername(), user.getPassword(), Set.of(new SimpleGrantedAuthority(user.getRole().getName())), user.getId());
        }
        throw new UsernameNotFoundException("User not found with the username " + username);
    }

}
