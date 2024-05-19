package es.deusto.ingenieria.sd.rmi.server.exceptions;

public class ErrorCreacionQr extends RuntimeException {

    public ErrorCreacionQr(String message){
        super(message);
    }

    public ErrorCreacionQr(String message, Throwable ex){
        super(message, ex);
    }
}
