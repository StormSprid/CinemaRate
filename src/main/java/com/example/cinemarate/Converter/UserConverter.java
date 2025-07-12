package com.example.cinemarate.Converter;

import com.example.cinemarate.DTO.UserDTO;
import com.example.cinemarate.Entity.UserEntity;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class UserConverter {

    public UserEntity fromDto(UserDTO dto) {
        return UserEntity.create(
                dto.getUsername(),
                dto.getEmail(),
                dto.getPassword()

        );
    }

}
