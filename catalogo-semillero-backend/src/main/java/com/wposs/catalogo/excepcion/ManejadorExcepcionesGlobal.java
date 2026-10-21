package com.wposs.catalogo.excepcion;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ManejadorExcepcionesGlobal {

    @ExceptionHandler(RecursoNoEncontradoException.class)
    public ResponseEntity<ErrorRespuesta> manejarNoEncontrado(RecursoNoEncontradoException ex, WebRequest request) {
        ErrorRespuesta error = new ErrorRespuesta(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                request.getDescription(false).replace("uri=", "")
        );
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorRespuesta> manejarValidaciones(MethodArgumentNotValidException ex, WebRequest request) {
        String mensaje = ex.getBindingResult().getFieldErrors().stream()
                .map(e -> e.getField() + ": " + e.getDefaultMessage())
                .collect(Collectors.joining(", "));

        ErrorRespuesta error = new ErrorRespuesta(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                mensaje,
                request.getDescription(false).replace("uri=", "")
        );
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorRespuesta> manejarValidaciones(ConstraintViolationException ex, WebRequest request) {
        String mensaje = ex.getConstraintViolations().stream()
                .map(v -> v.getPropertyPath() + ": " + v.getMessage())
                .collect(Collectors.joining(", "));

        ErrorRespuesta error = new ErrorRespuesta(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                mensaje,
                request.getDescription(false).replace("uri=", "")
        );
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RecursoDuplicadoException.class)
    public ResponseEntity<ErrorRespuesta> manejarDuplicado(RecursoDuplicadoException ex, WebRequest request) {
        ErrorRespuesta error = new ErrorRespuesta(
                LocalDateTime.now(),
                HttpStatus.CONFLICT.value(),
                ex.getMessage(),
                request.getDescription(false).replace("uri=", "")
        );
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorRespuesta> manejarConflictoPersistencia(
            DataIntegrityViolationException ex,
            WebRequest request
    ) {
        ErrorRespuesta error = new ErrorRespuesta(
                LocalDateTime.now(),
                HttpStatus.CONFLICT.value(),
                "Ya existe un registro con uno de los valores únicos proporcionados",
                request.getDescription(false).replace("uri=", "")
        );
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ErrorRespuesta> manejarAutenticacion(AuthenticationException ex, WebRequest request) {
        ErrorRespuesta error = new ErrorRespuesta(
                LocalDateTime.now(),
                HttpStatus.UNAUTHORIZED.value(),
                "Usuario o contraseña incorrectos",
                request.getDescription(false).replace("uri=", "")
        );
        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorRespuesta> manejarGeneral(Exception ex, WebRequest request) {
        ErrorRespuesta error = new ErrorRespuesta(
                LocalDateTime.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                ex.getMessage(),
                request.getDescription(false).replace("uri=", "")
        );
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
