package es.deusto.ingenieria.sd.rmi.server.dto;
import lombok.*;

import javax.jdo.annotations.PersistenceCapable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Data //poniendo esto, 
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ApiHabitacionDTO {
    private String nombre;
    private String descripcion;
    private int aforo;
    private ApiResponseDTO<ApiAlojamientoDTO> alojamiento;
    private ApiResponseList<ApiDisponibilidadDTO> disponibilidades;


}
