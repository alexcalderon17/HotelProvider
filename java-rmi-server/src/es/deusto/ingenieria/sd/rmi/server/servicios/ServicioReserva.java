package es.deusto.ingenieria.sd.rmi.server.servicios;

import java.util.List;
import java.util.Date;

import es.deusto.ingenieria.sd.rmi.server.dto.AlojamientoDTO;
import es.deusto.ingenieria.sd.rmi.server.dto.HabitacionDTO;
import es.deusto.ingenieria.sd.rmi.server.dto.Reserva;


public interface ServicioReserva {
    public Reserva crearReserva (String ClienteID, String Alojamiento, Date FechaInicio, Date FechaFin);
    public void cancelarReserva (Reserva ReservaID);
    public boolean modificarReserva (String reservaID, Date nuevaFechaInicio, Date nuevaFechaFin);
    //public List<Reserva> obtenerReservasUsuario (String usuarioID);
}