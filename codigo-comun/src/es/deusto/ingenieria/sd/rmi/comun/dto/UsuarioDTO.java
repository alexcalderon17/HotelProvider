package es.deusto.ingenieria.sd.rmi.comun.dto;

import lombok.*;
import java.io.Serializable;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO implements Serializable{

   
    private String nombre;
    private String apellido;
    private String DNI;
    private String correo;
    private String telefono;
    private String password;
    //private int codPostal;

    public UsuarioDTO (String correo){
        this.correo = correo;
    }

}
