package com.example.cinemarate.Controller;

import com.example.cinemarate.DTO.SignInRequest;
import com.example.cinemarate.DTO.SignUpRequest;
import com.example.cinemarate.Repository.UserRepository;
import com.example.cinemarate.Security.jwt.JwtCore;
import com.example.cinemarate.ServiceImpl.AuthServiceImpl;
import com.example.cinemarate.ServiceImpl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthServiceImpl authService;


    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @PostMapping("/signup")
    ResponseEntity<?> signup(@RequestBody SignUpRequest signUpRequest) {
        try {
            authService.register(signUpRequest);
        } catch (Exception e) {
            logger.warn(e.getMessage());
        }
        return ResponseEntity.ok("User registered");
    }

    @PostMapping("/signin")
    ResponseEntity<?> signIn(@RequestBody SignInRequest signInRequest) {
       String jwt = authService.login(signInRequest);
        return ResponseEntity.ok(jwt);
    }
}
