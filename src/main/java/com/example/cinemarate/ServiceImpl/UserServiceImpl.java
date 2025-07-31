package com.example.cinemarate.ServiceImpl;

import com.example.cinemarate.Converter.UserConverter;
import com.example.cinemarate.DTO.SignInRequest;
import com.example.cinemarate.DTO.SignUpRequest;
import com.example.cinemarate.DTO.UserDTO;
import com.example.cinemarate.Entity.Role;
import com.example.cinemarate.Entity.UserEntity;
import com.example.cinemarate.Exception.CustomException;
import com.example.cinemarate.Exception.ErrorModel;
//import com.example.cinemarate.Repository.SessionRepository;
import com.example.cinemarate.Repository.UserRepository;
//import com.example.cinemarate.Security.Session.Session;
import com.example.cinemarate.Security.jwt.JwtCore;
import com.example.cinemarate.Security.jwt.UserDetailsImpl;
import com.example.cinemarate.Service.UserService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {
    private UserRepository userRepository;




    @Override
    public void deleteUser(Long id) {
        userRepository.delete(userRepository.findById(id).orElseThrow());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByUsername(username).orElseThrow(()->new UsernameNotFoundException(
                String.format("User %s not found",username)
        ));
        return UserDetailsImpl.build(userEntity);
    }
}
