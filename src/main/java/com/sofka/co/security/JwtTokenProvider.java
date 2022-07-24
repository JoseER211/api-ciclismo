package com.sofka.co.security;

import com.sofka.co.exceptions.NotEqualsException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtTokenProvider {

    @Value("${app.jwt-secret}")
    private String jwtSecret;

    @Value("${app.jwt-expiration-milliseconds}")
    private Integer jwtExpirationInMilliseconds;



    public String generateToken(Authentication authentication) {
        String username = authentication.getName();
        Date date = new Date();
        Date expirationDate = new Date(date.getTime() + jwtExpirationInMilliseconds);

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, jwtSecret).
                compact();


    }

    public String getUsernameFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    public Boolean ValidatedToken(String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch (SignatureException exception) {
            throw new NotEqualsException(HttpStatus.BAD_REQUEST, "Invalid JWT signature");

        } catch (MalformedJwtException exception) {
            throw new NotEqualsException(HttpStatus.BAD_REQUEST, "Invalid JWT token");

        } catch (ExpiredJwtException exception) {
            throw new NotEqualsException(HttpStatus.BAD_REQUEST, "Expired JWT token");

        } catch (UnsupportedJwtException exception) {
            throw new NotEqualsException(HttpStatus.BAD_REQUEST, "Unsupported JWT token");

        } catch (IllegalArgumentException exception) {
            throw new NotEqualsException(HttpStatus.BAD_REQUEST, "JWT claims string is empty.");

        }

    }
}
