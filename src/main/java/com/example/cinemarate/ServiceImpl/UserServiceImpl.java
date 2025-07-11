package com.example.cinemarate.ServiceImpl;

import com.example.cinemarate.Converter.UserConverter;
import com.example.cinemarate.DTO.UserDTO;
import com.example.cinemarate.Entity.UserEntity;
import com.example.cinemarate.Repository.UserRepository;
import com.example.cinemarate.Service.UserService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserConverter converter;
    @Autowired
    private UserRepository repository;
    @Override
    public UserDTO register(UserDTO userDTO) {
        repository.save(converter.fromDto(userDTO));
        return userDTO;
    }

    @Override
    public void deleteUser(Long id) {

    }
}
