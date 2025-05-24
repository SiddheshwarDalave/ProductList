package com.example.ProductApplication.controller;

import com.example.ProductApplication.dto.UserDTO;
import com.example.ProductApplication.entity.User;
import com.example.ProductApplication.security.JwtUtil;
import com.example.ProductApplication.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public User register(@RequestBody User user){
        return myUserDetailsService.register(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody UserDTO userDTO){
     Authentication authentication= authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userDTO.getUsername(), userDTO.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

       List<String> roles= authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();
       UserDetails userDetails= myUserDetailsService.loadUserByUsername(userDTO.getUsername());

       String token= jwtUtil.generateToken(userDetails.getUsername(), roles);

       return token;
    }
}
