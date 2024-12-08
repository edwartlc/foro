package com.edwartlc.foro.domain.tema;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TemaRepository extends JpaRepository<Tema, Long> {

    boolean existsByTituloAndMensaje(String titulo, String mensaje);

    @Query("""
            SELECT t FROM Tema t 
            JOIN t.curso c 
            WHERE c.nombre = :curso 
            AND YEAR(t.fechaCreacion) = :anio
            """)
    Page<DatosDetalleTema> buscarTemaPorCursoYAnio(
            Pageable paginacion, String curso, Long anio);

    @Query("""
            SELECT CASE 
                WHEN COUNT(t) > 0
                THEN true
                ELSE false
                END
            FROM Tema t
            WHERE YEAR(t.fechaCreacion) = :anio
            """)
    boolean existsByYear(Long anio);
}
