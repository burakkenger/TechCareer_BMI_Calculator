package com.burakkenger.spring_bmi_calculator.configuration.filter;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.lang.Strings;
import io.jsonwebtoken.*;
import io.jsonwebtoken.lang.Strings;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Component
public class JwtVerifier extends OncePerRequestFilter{

    @Value("${jwt.secret-key}")
    private String secretKey;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String bearerToken = request.getHeader("Authorization");

        if (Strings.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            String token = bearerToken.replace("Bearer ","");

            try {
                Claims body = Jwts.parser().setSigningKey(secretKey.getBytes()).build().parseClaimsJws(token).getBody();
                List<Map<String, String>> authorities = (List<Map<String, String>>) body.get("authority");

                List<SimpleGrantedAuthority> grantedAuthorities =
                        authorities.stream().map(authority -> new SimpleGrantedAuthority("AUTHORITY_" + authority)).toList();

                Authentication authentication = new UsernamePasswordAuthenticationToken(
                        body.getIssuer(),
                        null,
                        grantedAuthorities
                );

                SecurityContextHolder.getContext().setAuthentication(authentication);

            } catch (JwtException exception) {
                exception.printStackTrace();
            }
        }

        filterChain.doFilter(request, response);
    }
}
