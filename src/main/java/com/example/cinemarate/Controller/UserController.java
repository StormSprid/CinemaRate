package com.example.cinemarate.Controller;
import com.example.cinemarate.Converter.UserConverter;
import com.example.cinemarate.DTO.SignInRequest;
import com.example.cinemarate.DTO.SignUpRequest;
import com.example.cinemarate.DTO.UserDTO;
import com.example.cinemarate.Entity.UserEntity;
import com.example.cinemarate.Repository.UserRepository;
import com.example.cinemarate.Security.jwt.JwtCore;
import com.example.cinemarate.ServiceImpl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;


@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    private final UserServiceImpl service;

    private  final UserConverter converter;



    @GetMapping("/{id}")
    public Optional<UserDTO> getUser(@PathVariable Long id) {
        return Optional.ofNullable(converter.toDto(userRepository.findById(id)));
    }



}
