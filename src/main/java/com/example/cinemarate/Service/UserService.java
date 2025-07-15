package com.example.cinemarate.Service;

import com.example.cinemarate.DTO.UserDTO;
import com.example.cinemarate.Entity.UserEntity;

public interface UserService {
    UserDTO register(UserDTO userDTO);
    UserDTO login(UserDTO userDTO);
    void deleteUser(Long id);
}
