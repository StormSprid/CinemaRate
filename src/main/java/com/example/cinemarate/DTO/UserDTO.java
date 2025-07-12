package com.example.cinemarate.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class UserDTO {
    private String username;
    private String email;
    @JsonIgnore
    private String password;

}
