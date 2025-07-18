package com.example.cinemarate.Controller;

import com.example.cinemarate.Converter.UserConverter;
import com.example.cinemarate.DTO.UserDTO;
import com.example.cinemarate.Entity.UserEntity;
import com.example.cinemarate.Repository.UserRepository;
import com.example.cinemarate.ServiceImpl.UserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.graphql.GraphQlProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserRepository repository;
    @Autowired
    private UserServiceImpl service;
    @Autowired
    private UserConverter converter;

    @GetMapping("/{id}")
    public Optional<UserDTO> getUser(@PathVariable Long id) {
        return Optional.ofNullable(converter.toDto(repository.findById(id)));
    }

    @PostMapping("/registration")
    public ResponseEntity<UserDTO> register(@Valid @RequestBody UserDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.register(dto));
    }
    @PostMapping("/login")
    public UserDTO login(@RequestBody UserDTO dto ){
        return service.login(dto);
    }
}
