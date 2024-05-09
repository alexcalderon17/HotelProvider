package es.deusto.ingenieria.sd.rmi.server.dto;

import lombok.*;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ApiResponseList<T> {


    List<ApiData<T>> data;

    
    
}
