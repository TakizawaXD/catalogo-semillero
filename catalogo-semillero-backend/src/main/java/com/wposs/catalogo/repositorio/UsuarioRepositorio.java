package com.wposs.catalogo.repositorio;

import com.wposs.catalogo.modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByUsuarioIgnoreCase(String usuario);

    boolean existsByUsuarioIgnoreCase(String usuario);

    boolean existsByCorreoIgnoreCase(String correo);
}
