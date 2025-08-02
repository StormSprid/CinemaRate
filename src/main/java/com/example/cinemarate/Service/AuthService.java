package com.example.cinemarate.Service;

import com.example.cinemarate.DTO.SignInRequest;
import com.example.cinemarate.DTO.SignUpRequest;
import com.example.cinemarate.DTO.UserDTO;

public interface AuthService {
    UserDTO register(SignUpRequest request);
    String login(SignInRequest request);
}
