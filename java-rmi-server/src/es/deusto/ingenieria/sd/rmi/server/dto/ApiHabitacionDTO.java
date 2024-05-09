package es.deusto.ingenieria.sd.rmi.server.dto;
import lombok.*;

@Data //poniendo esto, 
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ApiHabitacionDTO {
    private String nombre;
    private String descripcion;
    private int aforo;
    private ApiResponseDTO<ApiData<ApiAlojamientoDTO>> alojamiento;

}
