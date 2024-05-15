package es.deusto.ingenieria.sd.rmi.server.exceptions;

public class ErrorCreacionReserva extends RuntimeException {

    public ErrorCreacionReserva(String message){
        super(message);
    }

    public ErrorCreacionReserva(String message, Throwable ex){
        super(message, ex);
    }
}
