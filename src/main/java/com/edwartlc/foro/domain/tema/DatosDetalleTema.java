package com.edwartlc.foro.domain.tema;

import java.time.LocalDateTime;

public record DatosDetalleTema(
        String titulo, String mensaje, LocalDateTime fechaCreacion,
        boolean estado, Long idAutor, Long idCurso) {

    public DatosDetalleTema(Tema tema) {
        this(tema.getTitulo(), tema.getMensaje(),
                tema.getFechaCreacion(), tema.isEstado(),
                tema.getUsuario().getId(), tema.getCurso().getId());
    }

    public DatosDetalleTema(DatosDetalleTema datosDetalleTema) {
        this(datosDetalleTema.titulo, datosDetalleTema.mensaje(),
                datosDetalleTema.fechaCreacion, datosDetalleTema.estado,
                datosDetalleTema.idAutor(), datosDetalleTema.idCurso);
    }
}
