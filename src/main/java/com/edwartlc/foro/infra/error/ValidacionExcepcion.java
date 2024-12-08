package com.edwartlc.foro.infra.error;

public class ValidacionExcepcion extends RuntimeException {

    public ValidacionExcepcion(String mensaje) {
        super(mensaje);
    }
}
