package com.wposs.catalogo.servicio;

import com.wposs.catalogo.dto.auth.LoginRequest;
import com.wposs.catalogo.dto.auth.RegistroRequest;
import com.wposs.catalogo.dto.auth.TokenRespuesta;
import com.wposs.catalogo.dto.auth.UsuarioRespuesta;
import com.wposs.catalogo.excepcion.RecursoDuplicadoException;
import com.wposs.catalogo.modelo.Usuario;
import com.wposs.catalogo.repositorio.UsuarioRepositorio;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Locale;

@Service
public class ServicioAutenticacion {

    private final UsuarioRepositorio usuarioRepositorio;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final ServicioJwt servicioJwt;

    public ServicioAutenticacion(
            UsuarioRepositorio usuarioRepositorio,
            PasswordEncoder passwordEncoder,
            AuthenticationManager authenticationManager,
            ServicioJwt servicioJwt
    ) {
        this.usuarioRepositorio = usuarioRepositorio;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.servicioJwt = servicioJwt;
    }

    @Transactional
    public UsuarioRespuesta registrar(RegistroRequest request) {
        String nombreUsuario = request.usuario().trim().toLowerCase(Locale.ROOT);
        String correo = request.correo().trim().toLowerCase(Locale.ROOT);
        if (usuarioRepositorio.existsByUsuarioIgnoreCase(nombreUsuario)) {
            throw new RecursoDuplicadoException("El nombre de usuario ya está registrado");
        }
        if (usuarioRepositorio.existsByCorreoIgnoreCase(correo)) {
            throw new RecursoDuplicadoException("El correo ya está registrado");
        }

        Usuario usuario = new Usuario(
                nombreUsuario,
                correo,
                passwordEncoder.encode(request.contrasena())
        );
        return convertir(usuarioRepositorio.saveAndFlush(usuario));
    }

    public TokenRespuesta autenticar(LoginRequest request) {
        var autenticacion = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.usuario(), request.contrasena())
        );
        UserDetails principal = (UserDetails) autenticacion.getPrincipal();
        String token = servicioJwt.generarToken(principal.getUsername());

        return new TokenRespuesta(token, "Bearer", servicioJwt.obtenerExpiracionMs() / 1000);
    }

    @Transactional(readOnly = true)
    public UsuarioRespuesta obtenerUsuarioActual(String nombreUsuario) {
        Usuario usuario = usuarioRepositorio.findByUsuarioIgnoreCase(nombreUsuario)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
        return convertir(usuario);
    }

    private UsuarioRespuesta convertir(Usuario usuario) {
        return new UsuarioRespuesta(
                usuario.getId(),
                usuario.getUsuario(),
                usuario.getCorreo(),
                usuario.getRol()
        );
    }
}
