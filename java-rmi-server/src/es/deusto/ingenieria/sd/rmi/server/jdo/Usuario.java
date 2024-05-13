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
    private String dni;
    private String nombre;
    private String apellido;
    private String correo;
    private String telefono;
    private String password;
    @Persistent(mappedBy = "cliente")
    private List<Reserva> reservas;

}

