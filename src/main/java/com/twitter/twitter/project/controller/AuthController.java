package com.twitter.twitter.project.controller;

import com.twitter.twitter.project.service.CustomUserDetailsServiceImplementation;
import com.twitter.twitter.project.config.JwtProvider;
import com.twitter.twitter.project.exception.UserException;
import com.twitter.twitter.project.model.User;
import com.twitter.twitter.project.model.Varification;
import com.twitter.twitter.project.repository.UserRepository;
import com.twitter.twitter.project.response.AuthResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private CustomUserDetailsServiceImplementation customUserDetails;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> createUserHandler(@RequestBody User user) throws UserException{
        System.out.println("user" + user);

        String email = user.getEmail();
        String password = user.getPassword();
        String fullName = user.getFullName();
        String birthDate = user.getBirthDate();

        User isEmailExist = userRepository.findByEmail(email);

        if (isEmailExist != null){
            throw new UserException("Email is already used with another account");
        }

        User createdUser = new User();
        createdUser.setEmail(email);
        createdUser.setFullName(fullName);
        createdUser.setPassword(passwordEncoder.encode(password));
        createdUser.setBirthDate(birthDate);
        createdUser.setVarification(new Varification());

        User savedUser = userRepository.save(createdUser);
        Authentication authentication = new UsernamePasswordAuthenticationToken(email, password);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtProvider.generateToken(authentication);

        AuthResponse response = new AuthResponse(token, true);
        return new ResponseEntity<AuthResponse>(response, HttpStatus.CREATED);
    }

    @PostMapping("/signin")
    public ResponseEntity<AuthResponse> signin(@RequestBody User user){

        String username = user.getEmail();
        String password = user.getPassword();

        Authentication authentication = authenticate(username, password);

        String token = jwtProvider.generateToken(authentication);

        AuthResponse response = new AuthResponse(token, true);
        return new ResponseEntity<AuthResponse>(response, HttpStatus.ACCEPTED);
    }

    private Authentication authenticate(String username, String password) {
        UserDetails userDetails = customUserDetails.loadUserByUsername(username);
        if (userDetails == null){
            throw new BadCredentialsException("Invalid username...");
        }
        if(!passwordEncoder.matches(password, userDetails.getPassword())){
            throw new BadCredentialsException("Invalid username or password");
        }
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }
}
