package com.edwartlc.foro.controller;

import com.edwartlc.foro.domain.tema.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("temas")
@SecurityRequirement(name = "bearer-key")
public class TemaController {

    @Autowired
    private GestionTema gestionTema;
    @Autowired
    private ConsultaTemas consultaTemas;
    @Autowired
    private TemaRepository temaRepository;

    @PostMapping
    @Transactional
    public ResponseEntity registrarTema(
            @RequestBody @Valid DatosRegistroTema datosRegistroTema) {
        var detalleTema = gestionTema.registrarTema(
                datosRegistroTema);
        return ResponseEntity.ok(detalleTema);
    }

    @GetMapping
    public ResponseEntity<Page<DatosDetalleTema>> listadoTemas(
            @PageableDefault(size = 10, sort = {"fechaCreacion"},
                    direction = Sort.Direction.ASC)
            Pageable paginacion) {
        return ResponseEntity.ok(temaRepository.findAll(paginacion)
                .map(DatosDetalleTema::new));
    }

    @GetMapping("/filtro")
    public ResponseEntity<Page<DatosDetalleTema>> listaTemasPorCursoYAnio(
            @PageableDefault(size = 5, page = 1) Pageable paginacion,
            @RequestBody @Valid DatosConsultaTemas datosConsultaTemas) {
        return ResponseEntity.ok(consultaTemas.consultarTemasPorCursoAnio(
                paginacion, datosConsultaTemas));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosDetalleTema> retornarDatosTema(
            @PathVariable Long id) {
        Tema tema = temaRepository.getReferenceById(id);
        return ResponseEntity.ok(new DatosDetalleTema(tema));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity actualizarTema(@PathVariable Long id,
            @RequestBody @Valid DatosRegistroTema
                    datosRegistroTema) {
        var actualizaTema = gestionTema.actualizarTema(id,
                datosRegistroTema);
        return ResponseEntity.ok(actualizaTema);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void eliminarTema(@PathVariable Long id) {
        gestionTema.eliminarTema(id);
    }
}
