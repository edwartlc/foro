package com.edwartlc.foro.controller;

import com.edwartlc.foro.domain.tema.DatosDetalleTema;
import com.edwartlc.foro.domain.tema.DatosRegistroTema;
import com.edwartlc.foro.domain.tema.GestionTema;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class TemaControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private JacksonTester<DatosRegistroTema> datosRegistroTemaJson;
    @Autowired
    private JacksonTester<DatosDetalleTema> datosDetalleTemaJson;
    @MockBean
    private GestionTema gestionTema;

    @Test
    @DisplayName("Debería devolver código http 400 cuando la " +
            "request no sea válida")
    @WithMockUser
    void registrarTemaEscenario1() throws Exception {
        var response = mockMvc.perform(post("/temas"))
                .andReturn().getResponse();
        assertThat(response.getStatus())
                .isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Debería devolver código http 200 cuando la " +
            "request reciba un json válido")
    @WithMockUser
    void registrarTemaEscenario2() throws Exception {
        var fecha = LocalDateTime.now();
        var detalleTema = new DatosDetalleTema(
                "Título1", "Mensaje1",
                fecha, true, 1L,
                2L);
        when (gestionTema.registrarTema(any())).thenReturn(
                detalleTema);
        var response = mockMvc.perform(post(
                "/temas").contentType(MediaType
                        .APPLICATION_JSON)
                        .content(datosRegistroTemaJson.write(
                                new DatosRegistroTema(
                                        "Título1",
                                        "Mensaje1",
                                        1L, 2L))
                                .getJson()))
                .andReturn().getResponse();
        var jsonEsperado = datosDetalleTemaJson.write(
                detalleTema).getJson();
        assertThat(response.getStatus()).isEqualTo(HttpStatus
                .OK.value());
        assertThat(response.getContentAsString(StandardCharsets
                .UTF_8)).isEqualTo(
                jsonEsperado);
    }
}