package com.example.demo.config.security;

import com.example.demo.repository.user.UserEntity;
import com.example.demo.repository.user.UserRepository;
import com.example.demo.web.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var example = new UserEntity();
        example.setUsername(username);
        var user = userRepository.findOne(Example.of(example)).orElseThrow(NotFoundException::new);
        return new CustomUserDetails(user);
    }

}