CREATE TABLE usuarios(
    id bigint NOT NULL auto_increment,
    nombre varchar(100) NOT NULL,
    correo_electronico varchar(100) NOT NULL,
    clave varchar(300) NOT NULL,
    perfil varchar(100) NOT NULL,

    PRIMARY KEY (id)
);

CREATE TABLE cursos(
    id bigint NOT NULL auto_increment,
    nombre varchar(100) NOT NULL,
    categoria varchar(100) NOT NULL,

    PRIMARY KEY (id)
);

CREATE TABLE temas(
    id bigint NOT NULL auto_increment,
    titulo varchar(100) NOT NULL,
    mensaje varchar(200) NOT NULL,
    fecha_creacion datetime NOT NULL,
    estado tinyint NOT NULL,
    autor_id bigint NOT NULL,
    curso_id bigint NOT NULL,

    PRIMARY KEY (id),
    CONSTRAINT fk_temas_autor_id
        FOREIGN KEY (autor_id)
        REFERENCES usuarios (id),
    CONSTRAINT fk_cursos_curso_id
        FOREIGN KEY (curso_id)
        REFERENCES cursos (id)
);

CREATE TABLE respuestas(
    id bigint NOT NULL auto_increment,
    mensaje varchar(300) NOT NULL,
    tema_id bigint NOT NULL,
    fecha_creacion datetime NOT NULL,
    usuario_id bigint NOT NULL,
    solucion tinyint NOT NULL,

    PRIMARY KEY (id),
    CONSTRAINT fk_respuestas_tema_id
        FOREIGN KEY (tema_id)
        REFERENCES temas (id),
    CONSTRAINT fk_respuestas_usuario_id
        FOREIGN KEY (usuario_id)
        REFERENCES usuarios (id)
);