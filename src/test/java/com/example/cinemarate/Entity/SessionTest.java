package com.example.cinemarate.Entity;

import com.example.cinemarate.Security.Session.Session;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SessionTest {

    @Test
    void testExpiredSession(){
        Session session = new Session().issue(
                "Emil",
                Role.Admin
        );
        session.setIssueTime(LocalDateTime.now().minusMinutes(15));
        assertFalse(session.validateSession());
    }
    @Test
    void testValidSession(){
        Session session = new Session().issue(
                "Emil",
                Role.Admin
        );
        assertTrue(session.validateSession());
    }
    @Test
    void testToString(){
        Session session = new Session().issue(
                "Emil",
                Role.Admin
        );
        System.out.println(session);

    }}
