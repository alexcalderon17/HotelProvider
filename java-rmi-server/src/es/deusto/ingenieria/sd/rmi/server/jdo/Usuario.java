package es.deusto.ingenieria.sd.rmi.server.jdo;


import lombok.*;

import java.util.List;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.PrimaryKey;
import javax.jdo.annotations.Persistent;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@PersistenceCapable
public class Usuario {

    @PrimaryKey
    private String correo;
    private String password;
    private String nombre;
    private String apellido;
    private String telefono;
    private String dni;
    @Persistent(mappedBy = "cliente")
    private List<Reserva> reservas;

}

