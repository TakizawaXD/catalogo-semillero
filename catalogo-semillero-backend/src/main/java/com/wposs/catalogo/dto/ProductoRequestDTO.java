package com.wposs.catalogo.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

public record ProductoRequestDTO(
        @NotBlank(message = "El nombre no puede estar vacío")
        String nombre,
        
        String descripcion,
        
        @NotNull(message = "El precio es obligatorio")
        @DecimalMin(value = "0.0", inclusive = false, message = "El precio debe ser mayor a 0")
        BigDecimal precio,
        
        @NotNull(message = "El stock es obligatorio")
        @PositiveOrZero(message = "El stock debe ser positivo o cero")
        Integer stock,
        
        @NotBlank(message = "La categoría es obligatoria")
        String categoriaNombre
) {}
