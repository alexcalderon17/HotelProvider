package es.deusto.ingenieria.sd.rmi.server.exceptions;

public class ErrorLecturaBaseDatos extends RuntimeException {

    public ErrorLecturaBaseDatos(String message){
        super(message);
    }

    public ErrorLecturaBaseDatos(String message, Throwable ex){
        super(message, ex);
    }

}
