package com.edwartlc.foro.domain.tema;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosRegistroTema(

        @NotBlank
        String titulo,
        @NotBlank
        String mensaje,
        @NotNull
        @JsonAlias("autor_id")
        Long idUsuario,
        @NotNull
        Long idCurso
) {
}
