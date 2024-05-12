package es.deusto.ingenieria.sd.rmi.comun.dto;

import java.util.Date;
import lombok.*;
import javax.jdo.annotations.PersistenceCapable;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@Data
@PersistenceCapable
@AllArgsConstructor

public class ReservaDTO implements Serializable {
    
    private String cliente; // la idea es que sea el nombre del usuario que esta logeado en ese momento
    private String alojamiento;// la idea es que sea el nombre del alojamiento
    private String habitacion;// la idea es que sea el nombre del habitacion
    private LocalDate fechaInicio;
    private LocalDate fechaFin;

    //private boolean estaCancelada;
    }

    

    