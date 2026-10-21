package com.wposs.catalogo.servicio;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.Date;

@Service
public class ServicioJwt {

    private final SecretKey signingKey;
    private final long expiracionMs;

    public ServicioJwt(
            @Value("${app.jwt.secret-base64}") String secretBase64,
            @Value("${app.jwt.expiracion-ms}") long expiracionMs
    ) {
        this.signingKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretBase64));
        this.expiracionMs = expiracionMs;
    }

    public String generarToken(String nombreUsuario) {
        Instant ahora = Instant.now();
        return Jwts.builder()
                .subject(nombreUsuario)
                .issuedAt(Date.from(ahora))
                .expiration(Date.from(ahora.plusMillis(expiracionMs)))
                .signWith(signingKey)
                .compact();
    }

    public String obtenerNombreUsuario(String token) {
        return obtenerClaims(token).getSubject();
    }

    public long obtenerExpiracionMs() {
        return expiracionMs;
    }

    private Claims obtenerClaims(String token) {
        return Jwts.parser()
                .verifyWith(signingKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}
