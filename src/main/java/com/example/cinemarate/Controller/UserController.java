package com.example.cinemarate.Controller;

import com.example.cinemarate.DTO.UserDTO;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @GetMapping("/{id}")
    public UserDTO getUser(@PathVariable Long id){

    }
}
