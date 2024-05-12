package es.deusto.ingenieria.sd.rmi.server.dto;

import java.time.LocalDate;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Data //poniendo esto, 
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ApiDisponibilidadDTO {
    private LocalDate fecha_ini; 
    private LocalDate fecha_fin; 
    private float precio_base; 
    
}
