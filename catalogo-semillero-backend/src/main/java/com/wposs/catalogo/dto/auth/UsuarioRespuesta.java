package com.wposs.catalogo.dto.auth;

import com.wposs.catalogo.modelo.Rol;

public record UsuarioRespuesta(
        Long id,
        String usuario,
        String correo,
        Rol rol
) {
}
