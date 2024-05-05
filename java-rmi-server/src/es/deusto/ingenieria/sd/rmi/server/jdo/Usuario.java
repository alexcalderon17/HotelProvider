package es.deusto.ingenieria.sd.rmi.server.jdo;


import lombok.*;
import javax.jdo.annotations.PersistenceCapable;



@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@PersistenceCapable
public class Usuario {

    private String nombre;
    private String apellido;
    private String DNI;
    private String correo;
    private String telefono;
    private String password;
    //private int codPostal;

}