package es.deusto.ingenieria.sd.rmi.server.servicios;

import java.util.List;
import java.util.Date;

import es.deusto.ingenieria.sd.rmi.comun.dto.ReservaDTO;
import es.deusto.ingenieria.sd.rmi.comun.dto.UsuarioDTO;
import es.deusto.ingenieria.sd.rmi.server.dto.ApiData;
import es.deusto.ingenieria.sd.rmi.server.dto.ApiHabitacionDTO;
import es.deusto.ingenieria.sd.rmi.server.jdo.Reserva;


public interface ServicioReserva {
    public void guardarReserva(ReservaDTO reservaDTO, UsuarioDTO usuario);
    public Reserva leerReserva(String codigo);
}