package com.example.cinemarate.Service;

import com.example.cinemarate.Security.Session.Session;

import java.util.UUID;

public interface SessionService {
    Session getSession(UUID id);
}
