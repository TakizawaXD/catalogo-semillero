package com.wposs.catalogo.dto.auth;

public record TokenRespuesta(
        String accessToken,
        String tokenType,
        long expiresIn
) {
}
