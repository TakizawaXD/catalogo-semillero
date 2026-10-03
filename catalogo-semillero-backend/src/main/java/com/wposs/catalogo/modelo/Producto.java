package com.wposs.catalogo.modelo;

import java.math.BigDecimal;

public record Producto(
    Long id,
    String nombre,
    String descripcion,
    BigDecimal precio,
    String categoria,
    int stock
) {}
