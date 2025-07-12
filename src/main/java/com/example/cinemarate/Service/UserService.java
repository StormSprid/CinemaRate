package com.example.cinemarate.Service;

import com.example.cinemarate.DTO.UserDTO;
import com.example.cinemarate.Entity.UserEntity;

public interface UserService {
    UserEntity register(UserDTO userDTO);
    void deleteUser(Long id);
}
