package com.example.cinemarate.ServiceImpl;

import com.example.cinemarate.Converter.UserConverter;
import com.example.cinemarate.DTO.UserDTO;
import com.example.cinemarate.Entity.Role;
import com.example.cinemarate.Entity.UserEntity;
import com.example.cinemarate.Repository.SessionRepository;
import com.example.cinemarate.Repository.UserRepository;
import com.example.cinemarate.Security.Session.Session;
import com.example.cinemarate.Service.UserService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserConverter converter;

    private UserRepository repository;
    private SessionRepository sessionRepository;
    @Override
    public UserDTO register(UserDTO userDTO) {
        UserEntity userToCreate = converter.fromDto(userDTO);
        if(userDTO.getUsername().contains("admin")){
            userToCreate.setRole(Role.Admin);
        }
        repository.save(userToCreate);
        return userDTO;
    }

    @Override
    public UserDTO login(UserDTO userDTO) {
        UserDTO user = null;

        Optional<UserEntity> optionalUser = repository
                .findByEmailAndPassword(
                        userDTO.getEmail(),
                        userDTO.getPassword()
                );
        if(optionalUser.isPresent()){
            Role role = optionalUser.get().getRole();
            user = converter.toDto(optionalUser);
            Session session = new Session().issue(user.getUsername(),role);
            sessionRepository.save(session);
            user.setSessionId(session.getId());
        }
        return user;
    }

    @Override
    public void deleteUser(Long id) {
        repository.delete(repository.findById(id).orElseThrow());
    }
}
