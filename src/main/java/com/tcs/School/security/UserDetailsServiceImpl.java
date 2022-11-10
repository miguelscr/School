package com.tcs.School.security;

import com.tcs.School.model.UserModel;
import com.tcs.School.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserModel user = userRepository.findOneByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("The user " + email + " is not founded"));
        return new UserDetailsImpl(user);
    }
}