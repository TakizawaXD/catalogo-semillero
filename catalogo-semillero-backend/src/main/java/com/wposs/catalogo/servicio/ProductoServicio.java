package com.wposs.catalogo.servicio;

import com.wposs.catalogo.dto.ProductoRequestDTO;
import com.wposs.catalogo.dto.ProductoResponseDTO;
import com.wposs.catalogo.excepcion.RecursoNoEncontradoException;
import com.wposs.catalogo.mapper.ProductoMapper;
import com.wposs.catalogo.modelo.Categoria;
import com.wposs.catalogo.modelo.Producto;
import com.wposs.catalogo.repositorio.CategoriaRepositorio;
import com.wposs.catalogo.repositorio.ProductoRepositorio;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@SuppressWarnings("null")
public class ProductoServicio {

    private final ProductoRepositorio productoRepositorio;
    private final CategoriaRepositorio categoriaRepositorio;
    private final ProductoMapper mapper;

    public ProductoServicio(ProductoRepositorio productoRepositorio, 
                            CategoriaRepositorio categoriaRepositorio, 
                            ProductoMapper mapper) {
        this.productoRepositorio = productoRepositorio;
        this.categoriaRepositorio = categoriaRepositorio;
        this.mapper = mapper;
    }

    @Transactional(readOnly = true)
    public List<ProductoResponseDTO> listarTodos() {
        return productoRepositorio.findAllConCategoria().stream()
                .map(mapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ProductoResponseDTO buscarPorId(Long id) {
        return productoRepositorio.findById(id)
                .map(mapper::toResponseDTO)
                .orElseThrow(() -> new RecursoNoEncontradoException("Producto con ID " + id + " no encontrado"));
    }

    @Transactional
    public ProductoResponseDTO crear(ProductoRequestDTO dto) {
        Categoria categoria = categoriaRepositorio.findByNombreIgnoreCase(dto.categoriaNombre())
                .orElseGet(() -> categoriaRepositorio.save(new Categoria(dto.categoriaNombre())));
        
        Producto producto = mapper.toEntity(dto, categoria);
        Producto guardado = productoRepositorio.save(producto);
        
        return mapper.toResponseDTO(guardado);
    }

    @Transactional
    public ProductoResponseDTO actualizar(Long id, ProductoRequestDTO dto) {
        Producto existente = productoRepositorio.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Producto con ID " + id + " no encontrado"));
        
        Categoria categoria = categoriaRepositorio.findByNombreIgnoreCase(dto.categoriaNombre())
                .orElseGet(() -> categoriaRepositorio.save(new Categoria(dto.categoriaNombre())));
        
        existente.setNombre(dto.nombre());
        existente.setDescripcion(dto.descripcion());
        existente.setPrecio(dto.precio());
        existente.setStock(dto.stock());
        existente.setCategoria(categoria);
        
        return mapper.toResponseDTO(productoRepositorio.save(existente));
    }

    @Transactional
    public void eliminar(Long id) {
        if (!productoRepositorio.existsById(id)) {
            throw new RecursoNoEncontradoException("Producto con ID " + id + " no encontrado");
        }
        productoRepositorio.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<ProductoResponseDTO> filtrarPorCategoria(String categoriaNombre) {
        return productoRepositorio.findByCategoriaNombreIgnoreCase(categoriaNombre).stream()
                .map(mapper::toResponseDTO)
                .collect(Collectors.toList());
    }
}
