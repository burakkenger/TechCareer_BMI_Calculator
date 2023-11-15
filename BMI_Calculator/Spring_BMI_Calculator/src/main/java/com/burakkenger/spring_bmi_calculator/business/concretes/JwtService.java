package com.burakkenger.spring_bmi_calculator.business.concretes;

import io.jsonwebtoken.Jwt;
import com.burakkenger.spring_bmi_calculator.business.abstracts.IJwtService;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtService implements IJwtService {

    @Value("${jwt.secret-key}")
    private String secretKey;

    @Value("${jwt.token-expiration-time}")
    private int tokenExpirationTime;
    @Override
    public String generateJwtToken(User principal) {
        return Jwts.builder()
                .setSubject(principal.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(
                        new Date(
                                new Date().getTime() + tokenExpirationTime
                        )
                )
                .claim("authority", principal.getAuthorities())
                .signWith(SignatureAlgorithm.HS512, secretKey.getBytes())
                .compact();
    }
}
