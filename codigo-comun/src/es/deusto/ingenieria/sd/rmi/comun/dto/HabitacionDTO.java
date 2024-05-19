package es.deusto.ingenieria.sd.rmi.comun.dto;

import lombok.*;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class HabitacionDTO implements Serializable {
    private int id;
    private String nombre;
    private String descripcion;
    private int aforo;
}
