package es.deusto.ingenieria.sd.rmi.server.servicios;

import java.rmi.RemoteException;
import java.util.List;

import es.deusto.ingenieria.sd.rmi.comun.dto.AlojamientoDTO;
import es.deusto.ingenieria.sd.rmi.comun.dto.HabitacionDTO;
import es.deusto.ingenieria.sd.rmi.server.dto.ApiData;
import es.deusto.ingenieria.sd.rmi.server.dto.HabitacionDTO;

public interface ServicioAlojamientos {
    List<HabitacionDTO> obtenerHabitaciones();

    List<AlojamientoDTO> obtenerAlojamientos();


    
    
}
