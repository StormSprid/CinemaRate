package com.example.cinemarate.Controller;

import com.example.cinemarate.Converter.UserConverter;
import com.example.cinemarate.DTO.UserDTO;
import com.example.cinemarate.Entity.UserEntity;
import com.example.cinemarate.Repository.UserRepository;
import com.example.cinemarate.ServiceImpl.SessionServiceImpl;
import com.example.cinemarate.ServiceImpl.UserServiceImpl;
import jakarta.servlet.http.Cookie;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.graphql.GraphQlProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository repository;

    private final UserServiceImpl service;

    private  final UserConverter converter;

    private final SessionServiceImpl sessionService;


    @GetMapping("/me/name")
    public String getMyName(@RequestParam UUID uuid){
        return sessionService.getNameById(uuid);
    }

    @GetMapping("/{id}")
    public Optional<UserDTO> getUser(@PathVariable Long id) {
        return Optional.ofNullable(converter.toDto(repository.findById(id)));
    }

    @PostMapping("/registration")
    public ResponseEntity<UserDTO> register(@Valid @RequestBody UserDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.register(dto));
    }
    @PostMapping("/login")
    public ResponseEntity<UserDTO> login(@RequestBody UserDTO dto ){
//        Cookie cookie = new Cookie("sessionId", dto.getSessionId().toString());
//        cookie.setHttpOnly(true);  // защита от XSS
//        cookie.setPath("/");       // доступна для всех URL
//        cookie.setMaxAge(7 * 24 * 60 * 60); // неделя
//
//        response.addCookie(cookie);
        return ResponseEntity.ok(service.login(dto));
    }


    @GetMapping("/getName")
    public ResponseEntity<String> getUserName(@RequestHeader("X-Session-Id") UUID id ){
        System.out.println("Get name trigger");
        return ResponseEntity.status(HttpStatus.OK).body(sessionService.getNameById(id));
    }
    @PostMapping("/logout")
    public ResponseEntity<String> logoutUser(@RequestParam UUID uuid){
        if(sessionService.logout(uuid)){
            return ResponseEntity.ok("Logout successful");

        } else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Session not found");
        }
    }
}
