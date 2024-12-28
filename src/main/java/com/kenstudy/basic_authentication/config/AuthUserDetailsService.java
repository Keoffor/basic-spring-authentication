package com.kenstudy.basic_authentication.config;

import com.kenstudy.basic_authentication.entity.UserInfo;
import com.kenstudy.basic_authentication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuthUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       Optional<UserInfo> userInfo = repository.findByName(username);
         return userInfo.map(AuthUserDetails::new)
                 .orElseThrow(() -> new UsernameNotFoundException("user not found "+username));
    }
}
