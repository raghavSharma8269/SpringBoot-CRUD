package com.example.SpringFinalProject.security.services;

import com.example.SpringFinalProject.security.CustomUser;
import com.example.SpringFinalProject.security.CustomUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    
    private final CustomUserRepository userRepository;
    private final Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);
    
    public CustomUserDetailsService(CustomUserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        logger.info("Executing " + getClass() + " input: " + username);

        CustomUser user = userRepository.findById(username)
            .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
        
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getAuthority().name()));
        
        return new User(
            user.getUsername(),
            user.getPassword(),
            authorities
        );
    }
}