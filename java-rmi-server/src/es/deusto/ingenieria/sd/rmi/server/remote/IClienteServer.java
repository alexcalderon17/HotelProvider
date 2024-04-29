package es.deusto.ingenieria.sd.rmi.server.remote;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;

import es.deusto.ingenieria.sd.rmi.server.dto.AlojamientoDTO;
import es.deusto.ingenieria.sd.rmi.server.dto.HabitacionDTO;
import es.deusto.ingenieria.sd.rmi.server.dto.ReservaDTO;

public interface IClienteServer extends Remote {

    public ReservaDTO crearReserva(String ClienteID, String Alojamiento, Date FechaInicio, Date FechaFin)
            throws RemoteException;

    public void cancelarReserva(ReservaDTO ReservaID) throws RemoteException;

    public boolean modificarReserva(String reservaID, Date nuevaFechaInicio, Date nuevaFechaFin) throws RemoteException;
    // public List<Reserva> obtenerReservasUsuario (String usuarioID);

    public List<HabitacionDTO> obtenerHabitaciones() throws RemoteException;

    public String obtenerAlojamientos() throws RemoteException;

}