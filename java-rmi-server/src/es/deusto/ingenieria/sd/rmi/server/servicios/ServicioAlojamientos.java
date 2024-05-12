package es.deusto.ingenieria.sd.rmi.server.servicios;

import java.rmi.RemoteException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import es.deusto.ingenieria.sd.rmi.comun.dto.AlojamientoDTO;
import es.deusto.ingenieria.sd.rmi.comun.dto.HabitacionDTO;
import es.deusto.ingenieria.sd.rmi.server.dto.ApiData;
import es.deusto.ingenieria.sd.rmi.server.dto.ApiHabitacionDTO;

public interface ServicioAlojamientos {
    List<HabitacionDTO> obtenerHabitaciones(int IdAlojamientoSeleccionado, LocalDate fechaIni, LocalDate fechaFin);

    List<AlojamientoDTO> obtenerAlojamientos();


    
    
}
