package es.deusto.ingenieria.sd.rmi.server.exceptions;

public class ErrorLogin extends RuntimeException{

    public ErrorLogin(String message){
        super(message);
    }

    public ErrorLogin(String message, Throwable ex){
        super(message, ex);
    }

}
