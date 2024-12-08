package com.edwartlc.foro.domain.tema;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosConsultaTemas(

        @NotBlank
        String nombreCurso,
        @NotNull
        Long anio
) {
}
