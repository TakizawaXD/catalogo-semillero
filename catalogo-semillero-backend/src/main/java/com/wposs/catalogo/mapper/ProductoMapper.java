package com.wposs.catalogo.mapper;

import com.wposs.catalogo.dto.ProductoRequestDTO;
import com.wposs.catalogo.dto.ProductoResponseDTO;
import com.wposs.catalogo.modelo.Categoria;
import com.wposs.catalogo.modelo.Producto;
import org.springframework.stereotype.Component;

@Component
public class ProductoMapper {

    public ProductoResponseDTO toResponseDTO(Producto producto) {
        if (producto == null) {
            return null;
        }
        
        String categoriaNombre = producto.getCategoria() != null ? producto.getCategoria().getNombre() : null;
        
        return new ProductoResponseDTO(
                producto.getId(),
                producto.getNombre(),
                producto.getDescripcion(),
                producto.getPrecio(),
                producto.getStock(),
                categoriaNombre
        );
    }

    public Producto toEntity(ProductoRequestDTO dto, Categoria categoria) {
        if (dto == null) {
            return null;
        }
        
        return new Producto(
                dto.nombre(),
                dto.descripcion(),
                dto.precio(),
                dto.stock(),
                categoria
        );
    }
}
