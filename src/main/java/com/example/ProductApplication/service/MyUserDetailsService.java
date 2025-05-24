package com.example.ProductApplication.service;

import com.example.ProductApplication.entity.User;
import com.example.ProductApplication.repository.UserRepository;
import com.example.ProductApplication.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    public User register(User user) {
        // Need to encrypt the password before save// Can't save the raw password
        String hashed= new BCryptPasswordEncoder(12).encode(user.getPassword());
        user.setPassword(hashed);
        userRepository.save(user);
        return user;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       Optional<User> user= userRepository.findByUsername(username);
       if(user.isEmpty()) throw new UsernameNotFoundException("User detail missing in the Database");
       return new UserPrincipal(user.get());
    }
}
