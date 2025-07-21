package com.example.cinemarate.Controller;

import com.example.cinemarate.Converter.UserConverter;
import com.example.cinemarate.DTO.UserDTO;
import com.example.cinemarate.Entity.UserEntity;
import com.example.cinemarate.Repository.UserRepository;
import com.example.cinemarate.ServiceImpl.SessionServiceImpl;
import com.example.cinemarate.ServiceImpl.UserServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.graphql.GraphQlProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository repository;

    private final UserServiceImpl service;

    private  final UserConverter converter;

    private final SessionServiceImpl sessionService;

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


    @GetMapping("/getName")
    public ResponseEntity<String> getUserName(@RequestHeader("X-Session-Id") UUID id ){
        System.out.println("Get name trigger");
        return ResponseEntity.status(HttpStatus.OK).body(sessionService.getNameById(id));
    }
}
