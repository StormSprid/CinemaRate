package com.example.cinemarate.ServiceImpl;

import com.example.cinemarate.Repository.SessionRepository;
import com.example.cinemarate.Security.Session.Session;
import com.example.cinemarate.Service.SessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;
@Service
@RequiredArgsConstructor
public class SessionServiceImpl implements SessionService {
    private final SessionRepository sessionRepository;
    @Override
    public Session getSession(UUID id) {
        return sessionRepository.findById(id).orElse(null);
    }

    @Override
    public String getNameById(UUID id) {
        System.out.println("Searching session for ID = " + id);
        return sessionRepository.findNameById(id).orElseThrow();
    }
}
