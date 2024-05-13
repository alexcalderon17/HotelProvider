package es.deusto.ingenieria.sd.rmi.server.jdo;


import lombok.*;
import javax.jdo.annotations.PersistenceCapable;

import es.deusto.ingenieria.sd.rmi.server.jdo.Usuario;

import java.time.LocalDate;
import java.util.Date;
import javax.jdo.annotations.Persistent;




@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@PersistenceCapable
public class Reserva {

    private String alojamiento;
    private String habitacion;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    @Persistent
    private Usuario cliente;

}