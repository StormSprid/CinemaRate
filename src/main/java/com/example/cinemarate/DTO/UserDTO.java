package com.example.cinemarate.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserDTO {
    @NotNull(message = "Username is mandatory")
    @NotEmpty(message = "Username cannot be empty")
    @Size(max = 30)
    private String username;
    @NotNull(message = "Email is mandatory")
    @Size(max = 30)
    @NotEmpty(message = "Email cannot be empty")
    private String email;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull(message = "Password is mandatory")
    @NotEmpty(message = "Password cannot be empty")
    @Size(max = 30)
    private String password;

}
