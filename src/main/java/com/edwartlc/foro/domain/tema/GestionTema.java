package com.edwartlc.foro.domain.tema;

import com.edwartlc.foro.domain.curso.CursoRepository;
import com.edwartlc.foro.domain.tema.reglas.registro.ValidadorTema;
import com.edwartlc.foro.domain.usuario.UsuarioRepository;
import com.edwartlc.foro.infra.error.ValidacionExcepcion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class GestionTema {

    @Autowired
    private TemaRepository temaRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private CursoRepository cursoRepository;
    @Autowired
    private List<ValidadorTema> validadorTemas;

    public DatosDetalleTema registrarTema(
            DatosRegistroTema datosRegistroTema) {
        if (!usuarioRepository.existsById(
                datosRegistroTema.idUsuario())) {
            throw new ValidacionExcepcion("¡No existe " +
                    "un usuario con el id indicado!");
        }
        if (!cursoRepository.existsById(
                datosRegistroTema.idCurso())) {
            throw new ValidacionExcepcion("¡No existe " +
                    "un curso con el id indicado!");
        }
        validadorTemas.forEach(v -> v.validar(
                datosRegistroTema));
        var autor = usuarioRepository.findById(
                datosRegistroTema.idUsuario()).get();
        var curso = cursoRepository.findById(
                datosRegistroTema.idCurso()).get();
        var tema = new Tema(null, datosRegistroTema.titulo(),
                datosRegistroTema.mensaje(), LocalDateTime.now(),
                true, autor, curso);
        temaRepository.save(tema);
        return new DatosDetalleTema(tema);
    }

    public Object actualizarTema(
            Long id, DatosRegistroTema datosRegistroTema) {
        Optional<Tema> tema = temaRepository.findById(id);
        if (tema.isPresent()) {
            if (!usuarioRepository.existsById(
                    datosRegistroTema.idUsuario())) {
                throw new ValidacionExcepcion("¡No existe " +
                        "un usuario con el id indicado!");
            }
            if (!cursoRepository.existsById(
                    datosRegistroTema.idCurso())) {
                throw new ValidacionExcepcion("¡No existe " +
                        "un curso con el id indicado!");
            }
            validadorTemas.forEach(v -> v.validar(
                    datosRegistroTema));
            var autor = usuarioRepository.findById(
                    datosRegistroTema.idUsuario()).get();
            var curso = cursoRepository.findById(
                    datosRegistroTema.idCurso()).get();
            tema.get().actualizarDatos(datosRegistroTema.titulo(),
                    datosRegistroTema.mensaje(), autor, curso);
        } else {
            throw new ValidacionExcepcion("¡No existe " +
                    "un tema con el id indicado!");
        }
        return new DatosDetalleTema(tema.get());
    }

    public void eliminarTema(Long id) {
        Optional<Tema> tema = temaRepository.findById(id);
        if (tema.isPresent()) {
            temaRepository.deleteById(id);
        } else {
            throw new ValidacionExcepcion("¡No existe " +
                    "un tema con el id indicado!");
        }
    }
}
