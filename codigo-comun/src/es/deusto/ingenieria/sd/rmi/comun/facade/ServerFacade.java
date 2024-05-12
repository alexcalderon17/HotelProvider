package es.deusto.ingenieria.sd.rmi.comun.facade;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import es.deusto.ingenieria.sd.rmi.comun.dto.AlojamientoDTO;
import es.deusto.ingenieria.sd.rmi.comun.dto.HabitacionDTO;
import es.deusto.ingenieria.sd.rmi.comun.dto.UsuarioDTO;
import es.deusto.ingenieria.sd.rmi.comun.dto.ReservaDTO;


// import es.deusto.ingenieria.sd.rmi.server.dto.AlojamientoDTO;
// import es.deusto.ingenieria.sd.rmi.server.dto.HabitacionDTO;

public interface ServerFacade extends Remote {

    // List<HabitacionDTO> obtenerHabitaciones() throws RemoteException;
    List<AlojamientoDTO>  obtenerAlojamientos() throws RemoteException;
    List<HabitacionDTO>  obtenerHabitaciones(int IdAlojamientoSeleccionado, LocalDate fechaini, LocalDate fechafin) throws RemoteException;


    boolean iniciarSesion(String usuario, String contrasenya) throws RemoteException;
    void registrarse(UsuarioDTO usuarioDTO) throws RemoteException;
    void guardarReserva (ReservaDTO reservaDTO) throws RemoteException;

}
