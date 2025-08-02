package com.example.cinemarate.Security.jwt;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class TokenFilter extends OncePerRequestFilter {
    private final JwtCore jwtCore;
    private final ApplicationContext context;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String jwt = null;
        String username = null;
        UserDetails userDetails = null;
        UsernamePasswordAuthenticationToken auth = null;
        try {
            String headerAuth = request.getHeader("Authorization");
            if(headerAuth!=null && headerAuth.startsWith("Bearer ")){
                 jwt = headerAuth.substring(7);
            }
            if(jwt!=null){
                 try{
                    username = jwtCore.getNameFromJwt(jwt);
                 }
                 catch (ExpiredJwtException e){
                     e.printStackTrace();
                 }
                 if(username != null && SecurityContextHolder.getContext().getAuthentication() ==null){
                     UserDetailsService userDetailsService = context.getBean(UserDetailsService.class);
                     userDetails = userDetailsService.loadUserByUsername(username);
                     auth = new UsernamePasswordAuthenticationToken(
                             userDetails,
                             null,
                             userDetails.getAuthorities()
                     );
                     System.out.println("Username from JWT: " + username);
                     System.out.println("UserDetails: " + userDetails);
                     System.out.println("Authentication: " + auth);
                     SecurityContextHolder.getContext().setAuthentication(auth);
                 }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        filterChain.doFilter(request,response);

    }
}
