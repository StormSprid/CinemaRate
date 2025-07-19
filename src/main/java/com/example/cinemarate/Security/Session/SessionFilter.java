package com.example.cinemarate.Security.Session;

import com.example.cinemarate.Repository.SessionRepository;
import io.jsonwebtoken.io.IOException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class SessionFilter extends OncePerRequestFilter {

    private final SessionRepository sessionRepository;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain)
            throws ServletException, IOException, java.io.IOException {

        String sessionId = request.getHeader("X-Session-Id");

        if (sessionId != null) {
            try {
                UUID uuid = UUID.fromString(sessionId);
                sessionRepository.findById(uuid).ifPresentOrElse(session -> {
                    if (session.validateSession()) {
                        // можно добавить в SecurityContext или ThreadLocal
                        System.out.println("Сессия валидна для пользователя: " + session.getUsername());
                    } else {
                        System.out.println("Сессия истекла");
                    }
                }, () -> {
                    System.out.println("Сессия не найдена");
                });
            } catch (IllegalArgumentException e) {
                System.out.println("Некорректный UUID");
            }
        }

        filterChain.doFilter(request, response);
    }
}

