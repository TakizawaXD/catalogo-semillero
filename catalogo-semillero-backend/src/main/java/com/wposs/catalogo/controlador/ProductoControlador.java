package com.wposs.catalogo.controlador;

import com.wposs.catalogo.dto.ProductoRequestDTO;
import com.wposs.catalogo.dto.ProductoResponseDTO;
import com.wposs.catalogo.servicio.ProductoServicio;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import jakarta.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/productos")
@Validated
@Tag(name = "Productos", description = "Consulta y administración del catálogo")
public class ProductoControlador {

    private final ProductoServicio servicio;
    private final Validator validator;

    public ProductoControlador(ProductoServicio servicio, Validator validator) {
        this.servicio = servicio;
        this.validator = validator;
    }

    @GetMapping
    @Operation(summary = "Listar los productos")
    public List<ProductoResponseDTO> obtenerTodos() {
        return servicio.listarTodos();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Consultar un producto por su identificador")
    public ResponseEntity<ProductoResponseDTO> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(servicio.buscarPorId(id));
    }

    @GetMapping("/categoria/{categoria}")
    @Operation(summary = "Filtrar productos por categoría")
    public List<ProductoResponseDTO> obtenerPorCategoria(@PathVariable String categoria) {
        return servicio.filtrarPorCategoria(categoria);
    }

    @PostMapping
    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "Crear un producto (requiere rol ADMIN)")
    public ResponseEntity<ProductoResponseDTO> crear(@Valid @RequestBody ProductoRequestDTO dto) {
        validarProducto(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(servicio.crear(dto));
    }

    @PutMapping("/{id}")
    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "Actualizar un producto (requiere rol ADMIN)")
    public ResponseEntity<ProductoResponseDTO> actualizar(@PathVariable Long id, @Valid @RequestBody ProductoRequestDTO dto) {
        validarProducto(dto);
        return ResponseEntity.ok(servicio.actualizar(id, dto));
    }

    private void validarProducto(ProductoRequestDTO dto) {
        Set<ConstraintViolation<ProductoRequestDTO>> violations = validator.validate(dto);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }

    @DeleteMapping("/{id}")
    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "Eliminar un producto (requiere rol ADMIN)")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        servicio.eliminar(id);
    }
}
