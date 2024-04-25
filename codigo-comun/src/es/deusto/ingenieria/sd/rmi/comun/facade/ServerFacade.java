package es.deusto.ingenieria.sd.rmi.comun.facade;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

// import es.deusto.ingenieria.sd.rmi.server.dto.AlojamientoDTO;
// import es.deusto.ingenieria.sd.rmi.server.dto.HabitacionDTO;

public interface ServerFacade extends Remote {

   // List<HabitacionDTO> obtenerHabitaciones() throws RemoteException;
    String obtenerAlojamientos() throws RemoteException;
    boolean iniciarSesion(String usuario, String contrasenya) throws RemoteException;
    void registrarse(String usuario, String contrasenya) throws RemoteException; 

}