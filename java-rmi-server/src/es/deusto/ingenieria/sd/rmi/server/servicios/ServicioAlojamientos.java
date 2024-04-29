package es.deusto.ingenieria.sd.rmi.server.servicios;

import java.util.List;

import es.deusto.ingenieria.sd.rmi.comun.dto.AlojamientoAtributes;
import es.deusto.ingenieria.sd.rmi.server.dto.ApiData;
import es.deusto.ingenieria.sd.rmi.server.dto.HabitacionDTO;

public interface ServicioAlojamientos {
    List<HabitacionDTO> obtenerHabitaciones();

    List<AlojamientoAtributes> obtenerAlojamientos();
}
