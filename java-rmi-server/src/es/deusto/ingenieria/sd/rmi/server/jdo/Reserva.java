package es.deusto.ingenieria.sd.rmi.server.jdo;


import lombok.*;
import javax.jdo.annotations.PersistenceCapable;
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
    private Date fechaInicio;
    private Date fechaFin;
    //private int codPostal;

}