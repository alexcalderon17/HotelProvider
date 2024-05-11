package es.deusto.ingenieria.sd.rmi.server.dto;

import lombok.*;
import java.util.List;

import javax.jdo.annotations.PersistenceCapable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ApiResponseList<T> {


    List<ApiData<T>> data;

    
    
}
