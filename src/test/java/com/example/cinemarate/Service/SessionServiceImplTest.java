package com.example.cinemarate.Service;

import com.example.cinemarate.Repository.SessionRepository;
import com.example.cinemarate.ServiceImpl.SessionServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.*;

public class SessionServiceImplTest {

    private SessionRepository sessionRepository;
    private SessionService sessionService;

    @BeforeEach
    void setUp(){
        sessionRepository = mock(SessionRepository.class);
        sessionService = new SessionServiceImpl(sessionRepository);
    }

    @Test
    void testGetNameById_found(){
        UUID id = UUID.randomUUID();
        String name = "user";
        when(sessionRepository.findNameById(id)).thenReturn(Optional.of(name));

        String result = sessionService.getNameById(id);
        assertEquals(name,result);
        verify(sessionRepository).findNameById(id);
    }
    @Test
    void testGetNameById_notFound(){
        UUID id = UUID.randomUUID();
        String name = "user";
        when(sessionRepository.findNameById(id)).thenReturn(Optional.of(name));
        UUID mock = UUID.randomUUID();
        String result = sessionService.getNameById(mock);
        assertNotEquals(name,result);
        verify(sessionRepository).findNameById(mock);

    }}
