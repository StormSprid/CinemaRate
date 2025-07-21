package com.example.cinemarate.Repository;

import com.example.cinemarate.Security.Session.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface SessionRepository extends JpaRepository<Session, UUID> {
    Optional<Session> findById(UUID id);
    @Query("SELECT s.username FROM Session s WHERE s.id = :id")
    Optional<String> findNameById(UUID id);

}
