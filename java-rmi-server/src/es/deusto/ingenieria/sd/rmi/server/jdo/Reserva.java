package es.deusto.ingenieria.sd.rmi.server.jdo;


import lombok.*;
import javax.jdo.annotations.PersistenceCapable;

import java.time.LocalDate;
import java.util.Date;




@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@PersistenceCapable
public class Reserva {

    private String cliente;
    private String alojamiento;
    private String habitacion;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    //private int codPostal;

}