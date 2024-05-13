package es.deusto.ingenieria.sd.rmi.server.dto;

import java.time.LocalDate;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Data //poniendo esto, 
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ApiDisponibilidadDTO {
    private String fecha_ini; 
    private String fecha_fin; 
    private float precio_base; 
    
}
