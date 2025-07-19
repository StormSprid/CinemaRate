package com.example.cinemarate.ServiceImpl;

import com.example.cinemarate.Repository.SessionRepository;
import com.example.cinemarate.Security.Session.Session;
import com.example.cinemarate.Service.SessionService;
import org.springframework.stereotype.Service;

import java.util.UUID;
@Service
public class SessionServiceImpl implements SessionService {
    private SessionRepository sessionRepository;
    @Override
    public Session getSession(UUID id) {
        return sessionRepository.findById(id).orElse(null);
    }
}
