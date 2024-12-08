package com.edwartlc.foro.domain.tema;

import com.edwartlc.foro.domain.curso.CursoRepository;
import com.edwartlc.foro.infra.error.ValidacionExcepcion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ConsultaTemas {

    @Autowired
    private TemaRepository temaRepository;
    @Autowired
    private CursoRepository cursoRepository;

    public Page<DatosDetalleTema> consultarTemasPorCursoAnio(
            Pageable paginacion, DatosConsultaTemas
            datosConsultaTemas) {
        if (!cursoRepository.existsByNombre(
                datosConsultaTemas.nombreCurso())) {
            throw new ValidacionExcepcion("¡No existe " +
                    "un curso con el nombre indicado!");
        }
        if (!temaRepository.existsByYear(
                datosConsultaTemas.anio())) {
            throw new ValidacionExcepcion("¡No hay " +
                    "cursos en el año indicado!");
        }
        return temaRepository
                .buscarTemaPorCursoYAnio(paginacion,
                        datosConsultaTemas.nombreCurso(),
                        datosConsultaTemas.anio())
                .map(DatosDetalleTema::new);
    }
}
