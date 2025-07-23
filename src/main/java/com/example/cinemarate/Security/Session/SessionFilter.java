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

        String sessionId = request.getHeader("sessionId");

        if (sessionId != null) {
            try {
                UUID uuid = UUID.fromString(sessionId);
                var sessionOptional = sessionRepository.findById(uuid);

                if (sessionOptional.isEmpty()) {
                    System.out.println("Сессия не найдена");
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    return;
                }

                var session = sessionOptional.get();
                if (!session.validateSession()) {
                    System.out.println("Сессия истекла");
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    return;
                }

                System.out.println("Сессия валидна для пользователя: " + session.getUsername());
                // здесь можно добавить пользователя в SecurityContext
                // или в ThreadLocal, если нужно
            } catch (IllegalArgumentException e) {
                System.out.println("Некорректный UUID");
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                return;
            }
        }

        // если всё ок — продолжаем цепочку фильтров
        filterChain.doFilter(request, response);
    }

}

