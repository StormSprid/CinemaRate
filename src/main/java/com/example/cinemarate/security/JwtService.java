package com.example.cinemarate.security;

import com.example.cinemarate.DTO.JwtAuthentificationDTO;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecurityException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * Service for generating JWT access and refresh tokens.
 * <p>
 * This class creates signed JWTs using a secret key specified in application properties.
 * Access tokens have short lifespan (1 minute),
 * refresh tokens have longer lifespan (1 day).
 */
@Component
public class JwtService {
    /**
     * Secret key for signing JWT tokens, encoded in Base64.
     */
    @Value("${JWT-TOKEN}")
    private String jwtSecret;
    private  static final Logger LOGGER = LogManager.getLogger(JwtService.class);

    public JwtAuthentificationDTO generateAuthToken(String email){
        JwtAuthentificationDTO jwtDto = new JwtAuthentificationDTO();
        jwtDto.setToken(generateJwtToken(email));
        jwtDto.setRefreshToken(generateRefreshToken(email));
        return jwtDto;
    }
    public JwtAuthentificationDTO refreshBaseToken(String email,String refreshToken){
        JwtAuthentificationDTO jwtDto = new JwtAuthentificationDTO();
        jwtDto.setToken(generateJwtToken(email));
        jwtDto.setRefreshToken(refreshToken);
        return jwtDto;
    }
    public String getEmailFromToken(String token){
        Claims claims = Jwts.parser()
                .verifyWith(getSignInKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
        return claims.getSubject();
    }

    public boolean validateJwtToken(String token){
        try {
            Jwts.parser()
                    .verifyWith(getSignInKey())
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
            return true;
        }
        catch (ExpiredJwtException expExp){
            LOGGER.error("Expired JwtException",expExp);
        }
        catch (UnsupportedJwtException e){
            LOGGER.error("Unsupported JwtException",e);
        }
        catch (MalformedJwtException e){
            LOGGER.error("Malformed JwtException",e);
        }
        catch (SecurityException e){
            LOGGER.error("Security JwtException",e);
        }
        catch (Exception e){
            LOGGER.error("Exception ",e);
        }
        return false;
    }


    /**
     * Generates a signed JWT access token valid for 1 minute.
     *
     * @param email user email to be set as subject
     * @return signed JWT token string
     */
    private String generateJwtToken(String email) {
        Date date = Date.from(LocalDateTime.now().plusMinutes(1).atZone(ZoneId.systemDefault()).toInstant());
        return Jwts.builder()
                .setSubject(email)
                .setExpiration(date)
                .signWith(getSignInKey())
                .compact();
    }
    /**
     * Generates a signed JWT refresh token valid for 1 day.
     *
     * @param email user email to be set as subject
     * @return signed JWT refresh token string
     */
    private String generateRefreshToken(String email) {
        Date date = Date.from(LocalDateTime.now().plusDays(1).atZone(ZoneId.systemDefault()).toInstant());
        return Jwts.builder()
                .setSubject(email)
                .setExpiration(date)
                .signWith(getSignInKey())
                .compact();
    }
    /**
     * Creates a secret key instance for signing JWT tokens.
     *
     * @return SecretKey instance
     */
    private SecretKey getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtSecret);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
