package es.deusto.ingenieria.sd.rmi.comun.dto;

import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AlojamientoDTO  implements Serializable{ 

    private int id;
    private String nombre;
    private String descripcion;
    private String direccion;
    

}