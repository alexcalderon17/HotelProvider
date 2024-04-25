package es.deusto.ingenieria.sd.rmi.server.facade;

import java.rmi.Remote;
import java.rmi.RemoteException;




public interface ServerFacade extends Remote{

   
    String obtenerAlojamientos() throws RemoteException;
    boolean iniciarSesion(String usuario, String contrasenya) throws RemoteException;
    void registrarse(String usuario, String contrasenya) throws RemoteException; 

}