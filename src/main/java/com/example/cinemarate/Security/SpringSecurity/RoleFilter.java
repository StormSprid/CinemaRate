package com.example.cinemarate.Security.SpringSecurity;

import com.example.cinemarate.Entity.Role;
import com.example.cinemarate.Security.Session.Session;
import com.example.cinemarate.Service.SessionService;
import com.example.cinemarate.ServiceImpl.SessionServiceImpl;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class RoleFilter implements Filter {


    private final SessionServiceImpl sessionService; // где ты хранишь сессии

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws ServletException, IOException {


        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        String path = request.getRequestURI();


//
//        // Проверяем, если путь admin — и при этом нет заголовка, сразу запрещаем
//        if (path.contains("admin")) {
//            String sessionIdHeader = request.getHeader("sessionId");
//            System.out.println("Session header = " + sessionIdHeader);
//
//            if (sessionIdHeader == null) {
//                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Session ID required");
//                return;
//            }
//
//            try {
//                UUID sessionId = UUID.fromString(sessionIdHeader);
//                Session session = sessionService.getSession(sessionId);
//                System.out.println("SESSION = " + session); // временно добавить
//                System.out.println("ROLE = " + session.getRole());
//
//
//                if (session == null || session.getRole() != Role.Admin) {
//                    response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access Denied");
//                    return;
//                }
//            } catch (IllegalArgumentException e) {
//                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid session ID format");
//                return;
//            }
//        }

        chain.doFilter(req, res);
    }


}

