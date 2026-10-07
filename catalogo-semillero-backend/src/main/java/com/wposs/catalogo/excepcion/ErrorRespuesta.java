package com.wposs.catalogo.excepcion;

import java.time.LocalDateTime;

public record ErrorRespuesta(
        LocalDateTime timestamp,
        int status,
        String error,
        String path
) {}
