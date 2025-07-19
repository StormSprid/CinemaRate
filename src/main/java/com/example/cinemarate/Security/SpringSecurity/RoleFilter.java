package com.example.cinemarate.Security.SpringSecurity;

import com.example.cinemarate.Entity.Role;
import com.example.cinemarate.Security.Session.Session;
import com.example.cinemarate.Service.SessionService;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.UUID;

@Component
public class RoleFilter implements Filter {

    @Autowired
    private SessionService sessionService; // где ты хранишь сессии

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws ServletException, IOException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        String sessionIdHeader = request.getHeader("X-Session-Id");

        if (sessionIdHeader != null) {
            try {
                UUID sessionId = UUID.fromString(sessionIdHeader);
                Session session = sessionService.getSession(sessionId);

                String path = request.getRequestURI();

                if (path.startsWith("/admin") && (session == null || session.getRole() != Role.Admin)) {
                    response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access Denied");
                    return;
                }

            } catch (IllegalArgumentException e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid session ID format");
                return;
            }
        }

        chain.doFilter(req, res);
    }


}

