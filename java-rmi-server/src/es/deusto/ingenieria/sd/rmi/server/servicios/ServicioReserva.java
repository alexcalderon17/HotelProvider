package es.deusto.ingenieria.sd.rmi.server.servicios;

import java.util.List;
import java.util.Date;

import es.deusto.ingenieria.sd.rmi.comun.dto.ReservaDTO;
import es.deusto.ingenieria.sd.rmi.server.dto.ApiData;
import es.deusto.ingenieria.sd.rmi.server.dto.HabitacionDTO;
import es.deusto.ingenieria.sd.rmi.server.jdo.Reserva;


public interface ServicioReserva {
    public void guardarReserva(ReservaDTO reservaDTO);

    //public void cancelarReserva(ReservaDTO ReservaID);

    //public boolean modificarReserva(String reservaID, Date nuevaFechaInicio, Date nuevaFechaFin);
    // public List<Reserva> obtenerReservasUsuario (String usuarioID);
}