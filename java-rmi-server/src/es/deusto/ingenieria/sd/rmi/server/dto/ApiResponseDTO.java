package es.deusto.ingenieria.sd.rmi.server.dto;

import java.util.List;
import lombok.*;

@Data //poniendo esto, 
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ApiResponseDTO<T> {

    ApiData<T> data;
    
}
