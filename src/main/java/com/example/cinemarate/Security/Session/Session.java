package com.example.cinemarate.Security.Session;

import com.example.cinemarate.Entity.Role;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Session {
    @Id
    private UUID id;
    @Column(name = "created_at")
    private LocalDateTime issueTime;
    private int lifeTime = 10;
    private String username;
    private Role role;

    public Session issue(String username,Role role){
        Session session = new Session();
        session.setId(UUID.randomUUID());
        session.setIssueTime(LocalDateTime.now());
        session.setUsername(username);
        session.setRole(role);
        return session;
    }

    public boolean validateSession(){
       return LocalDateTime.now().isBefore(issueTime.plusMinutes(lifeTime));
    }


}
