package com.edwartlc.foro.domain.tema.reglas.registro;

import com.edwartlc.foro.domain.tema.DatosRegistroTema;
import com.edwartlc.foro.domain.tema.TemaRepository;
import com.edwartlc.foro.infra.error.ValidacionExcepcion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TemaRepetido implements ValidadorTema {

    @Autowired
    private TemaRepository temaRepository;

    public void validar(DatosRegistroTema datosRegistroTema) {
        var combinacionTituloMensajeYaExiste = temaRepository
                .existsByTituloAndMensaje(datosRegistroTema.titulo(),
                        datosRegistroTema.mensaje());
        if (combinacionTituloMensajeYaExiste) {
            throw new ValidacionExcepcion("¡Ya existe " +
                    "un tema con está combinación de título y " +
                    "mensaje!");
        }
    }
}
