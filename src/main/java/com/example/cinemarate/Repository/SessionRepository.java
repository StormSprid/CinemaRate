package com.example.cinemarate.Repository;

import com.example.cinemarate.Security.Session.Session;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SessionRepository extends JpaRepository<Session, UUID> {
}
