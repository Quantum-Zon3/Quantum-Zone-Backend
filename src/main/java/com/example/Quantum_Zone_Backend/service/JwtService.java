package com.example.Quantum_Zone_Backend.service;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.example.Quantum_Zone_Backend.modelo.Administrador;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;

@Service
public class JwtService {
	@Value("${JWT_SECRET}")
	private String secretCode;

	private SecretKey jwtSecret;
	private final long jwtExpirationMs = 300000; // 5 minutos

	@PostConstruct
	public void init() {
		if (secretCode == null || secretCode.trim().isEmpty()) {
			throw new IllegalStateException("JWT_SECRET must be configured in application.properties");
		}

		this.jwtSecret = Keys.hmacShaKeyFor(secretCode.getBytes(StandardCharsets.UTF_8));
	}

	public String generateToken(Administrador administrador) {
		return Jwts.builder().setSubject(administrador.getCedula()).claim("nombre", administrador.getNombre())
				.setIssuedAt(new Date()).setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
				.signWith(jwtSecret, SignatureAlgorithm.HS256).compact();
	}

	public boolean validateJwtToken(String token) {
		try {
			Jwts.parserBuilder().setSigningKey(jwtSecret).build().parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public String extractToken(String authHeader) {
		if (authHeader != null && authHeader.startsWith("Bearer ")) {
			return authHeader.substring(7);
		}
		return null;
	}
	public String getNombreFromJwtToken(String token) {
		try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(jwtSecret)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            return claims.get("nombre", String.class);
        } catch (Exception e) {
            return null;
        }
	}
	public String getCedulaFromJwtToken(String token) {
		try {
			Claims claims = Jwts.parserBuilder().setSigningKey(jwtSecret).build().parseClaimsJws(token).getBody();
			return claims.getSubject();
		} catch (Exception e) {
			return null;
		}
	}
	

}
