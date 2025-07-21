package com.example.cinemarate.Service;

import com.example.cinemarate.Security.Session.Session;
import org.springframework.stereotype.Service;

import java.util.UUID;

public interface SessionService {
    Session getSession(UUID id);
    String getNameById(UUID id);
}
