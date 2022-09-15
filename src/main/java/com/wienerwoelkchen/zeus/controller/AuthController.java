package com.wienerwoelkchen.zeus.controller;

import java.util.Optional;

import com.wienerwoelkchen.zeus.entity.Token;
import com.wienerwoelkchen.zeus.entity.User;
import com.wienerwoelkchen.zeus.repository.UserRepository;
import com.wienerwoelkchen.zeus.request.AuthRequest;
import com.wienerwoelkchen.zeus.security.JwtTokenProvider;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController extends User {

    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    private AuthenticationManager authenticationManager;

    private JwtTokenProvider jwtTokenProvider;

    @PostMapping(value = "/register")
    public ResponseEntity<User> register(@RequestBody AuthRequest authRequest) {
        Optional<User> userOptional = userRepository.findUserByEmail(authRequest.getEmail());

        if (userOptional.isPresent()) {
            return ResponseEntity.badRequest().build();
        }

        User user = new User();
        user.setEmail(authRequest.getEmail());
        user.setPassword(passwordEncoder.encode(authRequest.getPassword()));
        user.setNickname(authRequest.getNickname());

        User created = userRepository.save(user);

        return ResponseEntity.ok(created);
    }

    @PostMapping(value = "/login")
    public ResponseEntity<Token> login(@RequestBody AuthRequest authRequest) {
        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(),
                        authRequest.getPassword()));
        Token token = new Token();
        token.setToken(jwtTokenProvider.generateToken(authentication));
        return ResponseEntity.ok(token);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable(value = "id") Long id) {
        Optional<User> userOptional = userRepository.findById(id);

        if (userOptional.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        userRepository.deleteById(id);

        return ResponseEntity.ok("user has been deleted");
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<String> updateUser(@PathVariable(value = "id") Long id, @RequestBody String nickname) {
        User user = userRepository.findById(id).orElseThrow();

        user.setNickname(nickname);
        userRepository.saveAndFlush(user);

        return ResponseEntity.ok("user nickname has been updated");
    }

}
