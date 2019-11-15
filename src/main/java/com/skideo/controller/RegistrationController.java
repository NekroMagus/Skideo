package com.skideo.controller;

import com.skideo.config.JwtTokenUtil;
import com.skideo.model.User;
import com.skideo.model.jwt.JwtResponse;
import com.skideo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistrationController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @PostMapping("/registration")
    public ResponseEntity<?> registration(@RequestBody User user){
        userService.addUser(user);
        final UserDetails userDetails = userDetailsService.loadUserByUsername(user.getLogin());
        final String token = jwtTokenUtil.generateToken(userDetails);
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getLogin(), user.getPassword()));
        return ResponseEntity.ok(new JwtResponse(token));
    }
}