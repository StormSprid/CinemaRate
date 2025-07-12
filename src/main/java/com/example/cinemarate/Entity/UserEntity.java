package com.example.cinemarate.Entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name= "app_user")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String email;
    private String password;
    private String dateOfCreation;
    @OneToMany(mappedBy = "user")
    private List<ReviewEntity> reviews = new ArrayList<>();

    public UserEntity create(String username,String email,String password){
        UserEntity u = new UserEntity();
        u.setUsername(username);
        u.setEmail(email);
        u.setPassword(password);
        u.setDateOfCreation();
        return u;
    }

    public void setUsername(String username) {
        if(username.isBlank()){
            throw  new IllegalArgumentException(String.format("Username %s is not valid.Error to create a User.",username));

        }
        this.username = username;
    }

    public void setEmail(String email) {
        if(email.isBlank()){
            throw  new IllegalArgumentException(String.format("Email %s is not valid.Error to create a User.",email));
        }
        this.email = email;
    }

    public void setPassword(String password) {
        if(password.isBlank() || password.length()<8){
            throw  new IllegalArgumentException(String.format("Password %s is not valid.Error to create a User.",password));
        }
        this.password = password;
    }

    public void setDateOfCreation() {
        this.dateOfCreation = LocalDateTime.now().toString();
    }
}
