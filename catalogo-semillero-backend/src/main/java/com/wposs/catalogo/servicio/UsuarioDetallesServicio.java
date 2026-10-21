package com.wposs.catalogo.servicio;

import com.wposs.catalogo.modelo.Usuario;
import com.wposs.catalogo.repositorio.UsuarioRepositorio;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioDetallesServicio implements UserDetailsService {

    private final UsuarioRepositorio usuarioRepositorio;

    public UsuarioDetallesServicio(UsuarioRepositorio usuarioRepositorio) {
        this.usuarioRepositorio = usuarioRepositorio;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String nombreUsuario) {
        Usuario usuario = usuarioRepositorio.findByUsuarioIgnoreCase(nombreUsuario)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        var autoridad = new SimpleGrantedAuthority("ROLE_" + usuario.getRol().name());

        return User.withUsername(usuario.getUsuario())
                .password(usuario.getContrasena())
                .authorities(autoridad)
                .disabled(!usuario.isActivo())
                .build();
    }
}
