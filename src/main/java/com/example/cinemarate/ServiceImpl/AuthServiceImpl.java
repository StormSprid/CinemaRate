package com.example.cinemarate.ServiceImpl;

import com.example.cinemarate.Converter.UserConverter;
import com.example.cinemarate.DTO.SignInRequest;
import com.example.cinemarate.DTO.SignUpRequest;
import com.example.cinemarate.DTO.UserDTO;
import com.example.cinemarate.Entity.UserEntity;
import com.example.cinemarate.Repository.UserRepository;
import com.example.cinemarate.Security.jwt.JwtCore;
import com.example.cinemarate.Service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {
    private final @Lazy AuthenticationManager authenticationManager;
    private final JwtCore jwtCore;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public UserDTO register(SignUpRequest signUpRequest) {
        if(userRepository.existsByUsername(signUpRequest.getUsername())){
            throw new IllegalArgumentException(String.format("User with username %s already exist",
                    signUpRequest.getUsername()));
        }
        if(userRepository.existsByEmail(signUpRequest.getEmail())){
            throw new IllegalArgumentException(String.format("User with email %s already exist",
                    signUpRequest.getEmail()));
        }
        String hashed = passwordEncoder.encode(signUpRequest.getPassword());
        UserEntity userEntity = UserEntity.create(
                signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                hashed
        );
        userRepository.save(userEntity);
        return UserConverter.toDto(Optional.of(userEntity));
    }

    @Override
    public String login(SignInRequest signInRequest) {
        Authentication authentication = null;
        try {
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInRequest.getUsername(), signInRequest.getPassword()));

        } catch (BadCredentialsException e) {
            System.out.println(e.getMessage());
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return jwtCore.generateToken(authentication);
    }
}
