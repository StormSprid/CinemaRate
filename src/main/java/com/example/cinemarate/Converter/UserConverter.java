package com.example.cinemarate.Converter;

import com.example.cinemarate.DTO.UserDTO;
import com.example.cinemarate.Entity.UserEntity;
import jakarta.persistence.EntityNotFoundException;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

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
    public UserDTO toDto(Optional<UserEntity> u){
        if(u.isPresent()) {
            var user =  u.get();
            UserDTO dto = new UserDTO();
            dto.setEmail(user.getEmail());
            dto.setUsername(user.getUsername());
            dto.setPassword(user.getPassword());
            return dto;
        }
        throw new EntityNotFoundException();
    }

}
