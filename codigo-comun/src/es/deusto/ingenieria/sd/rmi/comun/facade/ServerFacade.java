package es.deusto.ingenieria.sd.rmi.comun.facade;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import es.deusto.ingenieria.sd.rmi.comun.dto.AlojamientoAtributes;

// import es.deusto.ingenieria.sd.rmi.server.dto.AlojamientoDTO;
// import es.deusto.ingenieria.sd.rmi.server.dto.HabitacionDTO;

public interface ServerFacade extends Remote {

    // List<HabitacionDTO> obtenerHabitaciones() throws RemoteException;
    List<AlojamientoAtributes>  obtenerAlojamientos() throws RemoteException;

    boolean iniciarSesion(String usuario, String contrasenya) throws RemoteException;

    void registrarse (String nombre, String apellido, String DNI, String correo, String telefono, String password, int codPostal) throws RemoteException;
}
