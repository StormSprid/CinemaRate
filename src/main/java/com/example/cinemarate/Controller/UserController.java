package com.example.cinemarate.Controller;

import com.example.cinemarate.Converter.UserConverter;
import com.example.cinemarate.DTO.UserDTO;
import com.example.cinemarate.Entity.UserEntity;
import com.example.cinemarate.Repository.UserRepository;
import com.example.cinemarate.ServiceImpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Optional<UserEntity> getUser(@PathVariable Long id){
        return repository.findById(id);
    }
    @PostMapping("/registration")
    public UserDTO register(@RequestBody UserDTO dto){
        return service.register(dto);
    }
}
