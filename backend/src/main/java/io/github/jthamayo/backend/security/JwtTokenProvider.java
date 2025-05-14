package io.github.jthamayo.backend.security;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

public class JwtTokenProvider {

    private static final Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);

    @Value("${app.jwtSecret}")
    private String jwtSecret;

    @Value("${app.jwtExpirationInMs}")
    private int jwtExpirationInMs;
    
    private final Key key = Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));

    public String generateToken(Authentication authentication) {
	UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

	Date now = new Date();
	Date expirationDate = new Date(now.getTime() + jwtExpirationInMs);

	return Jwts.builder().setSubject(Long.toString(userPrincipal.getId())).setIssuedAt(now)
		.setExpiration(expirationDate).signWith(key, SignatureAlgorithm.HS512).compact();
    }

    public Long getUserIdFromJWT(String token) {
	Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
	return Long.parseLong(claims.getSubject());
    }

    public boolean validateToken(String authToken) {
	try {
	    Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(authToken);
	    return true;
	} catch (JwtException | IllegalArgumentException ex) {
	    logger.error("Invalid JWT token: {}", ex.getMessage());
	}
	return false;

    }
}
