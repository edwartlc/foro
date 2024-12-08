package com.edwartlc.foro.domain.tema;

import com.edwartlc.foro.domain.curso.Curso;
import com.edwartlc.foro.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity(name = "Tema")
@Table(name = "temas")
public class Tema {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensaje;
    @JoinColumn(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;
    private boolean estado;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "autor_id")
    private Usuario usuario;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "curso_id")
    private Curso curso;

    public void actualizarDatos(String titulo, String mensaje,
                                Usuario autor, Curso curso) {
        this.titulo = titulo;
        this.mensaje = mensaje;
        this.usuario = autor;
        this.curso = curso;
    }
}
