package com.wposs.catalogo.controlador;

import com.wposs.catalogo.dto.auth.LoginRequest;
import com.wposs.catalogo.dto.auth.RegistroRequest;
import com.wposs.catalogo.dto.auth.TokenRespuesta;
import com.wposs.catalogo.dto.auth.UsuarioRespuesta;
import com.wposs.catalogo.servicio.ServicioAutenticacion;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Autenticación", description = "Registro, inicio de sesión e identidad del usuario")
public class AuthControlador {

    private final ServicioAutenticacion servicioAutenticacion;

    public AuthControlador(ServicioAutenticacion servicioAutenticacion) {
        this.servicioAutenticacion = servicioAutenticacion;
    }

    @PostMapping("/registro")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Registrar una cuenta de usuario")
    public UsuarioRespuesta registrar(@Valid @RequestBody RegistroRequest request) {
        return servicioAutenticacion.registrar(request);
    }

    @PostMapping("/login")
    @Operation(summary = "Iniciar sesión y obtener un token JWT")
    public TokenRespuesta iniciarSesion(@Valid @RequestBody LoginRequest request) {
        return servicioAutenticacion.autenticar(request);
    }

    @GetMapping("/yo")
    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "Obtener la cuenta asociada al token")
    public UsuarioRespuesta obtenerUsuarioActual(@AuthenticationPrincipal UserDetails principal) {
        return servicioAutenticacion.obtenerUsuarioActual(principal.getUsername());
    }
}
