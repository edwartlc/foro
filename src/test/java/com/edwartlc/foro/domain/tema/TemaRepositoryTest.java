package com.edwartlc.foro.domain.tema;

import com.edwartlc.foro.domain.curso.Categoria;
import com.edwartlc.foro.domain.curso.Curso;
import com.edwartlc.foro.domain.usuario.Perfil;
import com.edwartlc.foro.domain.usuario.Usuario;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase
        .Replace.NONE)
@ActiveProfiles("test")
class TemaRepositoryTest {

    @Autowired
    private TemaRepository temaRepository;
    @Autowired
    private EntityManager em;

    @Test
    @DisplayName("No debería devolver elementos cuando hay temas " +
            "para el curso buscado, pero en un año diferente " +
            "al indicado")
    void buscarTemaPorCursoYAnioEscenario1() {
        // given or arrange
        var autor = registrarUsuario("Usuario1",
                "usuario1@gmail.com", "clave1",
                Perfil.Estudiante);
        var curso = registrarCurso("Curso1",
                Categoria.Formación);
        var anioAnterior = (long) LocalDate.now()
                .minusYears(1).getYear();
        registrarTema("Tema1", "Mensaje1",
                LocalDateTime.now(), true, autor, curso);
        // when or act
        var temaExiste = temaRepository.buscarTemaPorCursoYAnio(
                any(Pageable.class), curso.getNombre(), anioAnterior);
        // then or assert
        assertThat(temaExiste.getTotalElements()).isEqualTo(0);
    }

    @Test
    @DisplayName("Debería devolver los temas encontrados" +
            "cuando el curso y el año indicado son correctos")
    void buscarTemaPorCursoYAnioEscenario2() {
        // given or arrange
        var autor = registrarUsuario("Usuario1",
                "usuario1@gmail.com", "clave1",
                Perfil.Estudiante);
        var curso = registrarCurso("Curso1",
                Categoria.Formación);
        var anioAnterior = (long) LocalDate.now()
                .minusYears(1).getYear();
        registrarTema("Tema1", "Mensaje1",
                LocalDateTime.now().minusYears(1),
                true, autor, curso);
        // when or act
        var temaExiste = temaRepository.buscarTemaPorCursoYAnio(
                any(Pageable.class), curso.getNombre(), anioAnterior);
        // then or assert
        assertThat(temaExiste.getTotalElements()).isGreaterThan(0);
    }

    private void registrarTema(
            String titulo, String mensaje, LocalDateTime fecha,
            boolean estado, Usuario autor, Curso curso) {
        em.persist(new Tema(null, titulo, mensaje, fecha, estado,
                autor, curso));
    }

    private Usuario registrarUsuario(
            String nombre, String correoElectronico, String clave,
            Perfil perfil) {
        var autor = new Usuario(null, nombre, correoElectronico,
                clave, perfil);
        em.persist(autor);
        return autor;
    }

    private Curso registrarCurso(
            String nombre, Categoria categoria) {
        var curso = new Curso(null, nombre,
                categoria);
        em.persist(curso);
        return curso;
    }
}