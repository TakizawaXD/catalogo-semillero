package com.wposs.catalogo.seguridad;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@SuppressWarnings("null")
class AutenticacionSeguridadTest {

    private static final String CONTRASENA = "UnaClaveSegura123";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void registroGuardaUsuarioYNoExponeContrasena() throws Exception {
        String usuario = nuevoUsuario();

        mockMvc.perform(post("/api/auth/registro")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(registroJson(usuario)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.usuario").value(usuario))
                .andExpect(jsonPath("$.rol").value("USER"))
                .andExpect(jsonPath("$.contrasena").doesNotExist());
    }

    @Test
    void registroRechazaNombreDuplicado() throws Exception {
        String usuario = nuevoUsuario();
        registrar(usuario);

        mockMvc.perform(post("/api/auth/registro")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(registroJson(usuario)))
                .andExpect(status().isConflict());
    }

    @Test
    void registroConDatosInvalidosDevuelve400() throws Exception {
        mockMvc.perform(post("/api/auth/registro")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "usuario": "x",
                                  "correo": "correo-invalido",
                                  "contrasena": "corta"
                                }
                                """))
                .andExpect(status().isBadRequest());
    }

    @Test
    void loginConCredencialesIncorrectasDevuelve401() throws Exception {
        mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(loginJson(nuevoUsuario(), "incorrecta")))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void jwtPermiteConsultarLaCuentaActual() throws Exception {
        String usuario = nuevoUsuario();
        registrar(usuario);
        String token = obtenerToken(usuario);

        mockMvc.perform(get("/api/auth/yo")
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.usuario").value(usuario))
                .andExpect(jsonPath("$.rol").value("USER"))
                .andExpect(jsonPath("$.contrasena").doesNotExist());
    }

    @Test
    void rutaProtegidaSinTokenDevuelve401YConRolInsuficienteDevuelve403() throws Exception {
        mockMvc.perform(post("/api/productos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isUnauthorized());

        String usuario = nuevoUsuario();
        registrar(usuario);
        String token = obtenerToken(usuario);

        mockMvc.perform(post("/api/productos")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isForbidden());
    }

    @Test
    void tokenInvalidoDevuelve401() throws Exception {
        mockMvc.perform(get("/api/auth/yo")
                        .header("Authorization", "Bearer token-invalido"))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.status").value(401));
    }

    @Test
    void consultaDeProductosEsPublica() throws Exception {
        mockMvc.perform(get("/api/productos"))
                .andExpect(status().isOk());
    }

    @Test
    void corsPermitePreflightDesdeElFrontendLocal() throws Exception {
        mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders
                        .options("/api/productos")
                        .header("Origin", "http://localhost:4200")
                        .header("Access-Control-Request-Method", "GET"))
                .andExpect(status().isOk())
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers
                        .header().string("Access-Control-Allow-Origin", "http://localhost:4200"));
    }

    private void registrar(String usuario) throws Exception {
        mockMvc.perform(post("/api/auth/registro")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(registroJson(usuario)))
                .andExpect(status().isCreated());
    }

    private String obtenerToken(String usuario) throws Exception {
        MvcResult resultado = mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(loginJson(usuario, CONTRASENA)))
                .andExpect(status().isOk())
                .andReturn();

        JsonNode respuesta = objectMapper.readTree(resultado.getResponse().getContentAsString());
        return respuesta.get("accessToken").asString();
    }

    private String registroJson(String usuario) {
        return """
                {
                  "usuario": "%s",
                  "correo": "%s@example.com",
                  "contrasena": "%s"
                }
                """.formatted(usuario, usuario, CONTRASENA);
    }

    private String loginJson(String usuario, String contrasena) {
        return """
                {
                  "usuario": "%s",
                  "contrasena": "%s"
                }
                """.formatted(usuario, contrasena);
    }

    private String nuevoUsuario() {
        return "u" + UUID.randomUUID().toString().replace("-", "").substring(0, 12);
    }
}
