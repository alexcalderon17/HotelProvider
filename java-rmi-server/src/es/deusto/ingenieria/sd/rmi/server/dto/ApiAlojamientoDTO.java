package es.deusto.ingenieria.sd.rmi.server.dto;

import lombok.*;

@Data //poniendo esto, 
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ApiAlojamientoDTO {
    
    private String nombre;
    private String descripcion;
    private String direccion;
}
