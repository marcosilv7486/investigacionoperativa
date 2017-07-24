package com.marcosilv7.proyectoiop.configuracion;

/**
 * Created by marcosilveriocastro on 24/07/17.
 */
public class OperacionNoPermitidaException extends RuntimeException {
    public OperacionNoPermitidaException(String mensaje){
        super(mensaje);
    }
}
