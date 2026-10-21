package com.wposs.catalogo.seguridad;
import com.wposs.catalogo.servicio.ServicioJwt;
import com.wposs.catalogo.servicio.UsuarioDetallesServicio;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;
import org.jspecify.annotations.NonNull;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class FiltroJwt extends OncePerRequestFilter {

    private static final String PREFIJO_BEARER = "Bearer ";

    private final ServicioJwt servicioJwt;
    private final UsuarioDetallesServicio usuarioDetallesServicio;
    private final ManejadorErroresSeguridad manejadorErroresSeguridad;

    public FiltroJwt(
            ServicioJwt servicioJwt,
            UsuarioDetallesServicio usuarioDetallesServicio,
            ManejadorErroresSeguridad manejadorErroresSeguridad
    ) {
        this.servicioJwt = servicioJwt;
        this.usuarioDetallesServicio = usuarioDetallesServicio;
        this.manejadorErroresSeguridad = manejadorErroresSeguridad;
    }

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        String authorization = request.getHeader("Authorization");
        if (authorization == null || !authorization.startsWith(PREFIJO_BEARER)) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = authorization.substring(PREFIJO_BEARER.length()).trim();
        if (token.isEmpty()) {
            rechazar(request, response, null);
            return;
        }

        try {
            String nombreUsuario = servicioJwt.obtenerNombreUsuario(token);
            if (SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails usuario = usuarioDetallesServicio.loadUserByUsername(nombreUsuario);
                if (!usuario.isEnabled()) {
                    rechazar(request, response, null);
                    return;
                }
                var autenticacion = new UsernamePasswordAuthenticationToken(
                        usuario, null, usuario.getAuthorities());
                autenticacion.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(autenticacion);
            }
        } catch (JwtException | IllegalArgumentException exception) {
            rechazar(request, response, exception);
            return;
        } catch (org.springframework.security.core.userdetails.UsernameNotFoundException exception) {
            rechazar(request, response, exception);
            return;
        }

        filterChain.doFilter(request, response);
    }

    private void rechazar(
            HttpServletRequest request,
            HttpServletResponse response,
            Exception causa
    ) throws IOException {
        SecurityContextHolder.clearContext();
        var exception = causa == null
                ? new InsufficientAuthenticationException("Token Bearer vacío")
                : new InsufficientAuthenticationException("Token JWT inválido", causa);
        manejadorErroresSeguridad.commence(request, response, exception);
    }
}
