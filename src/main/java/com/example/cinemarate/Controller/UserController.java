package com.example.cinemarate.Controller;

import com.example.cinemarate.DTO.UserDTO;
import com.example.cinemarate.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private User

    @GetMapping("/{id}")
    public UserDTO getUser(@PathVariable Long id){
        return null;
    }
}
