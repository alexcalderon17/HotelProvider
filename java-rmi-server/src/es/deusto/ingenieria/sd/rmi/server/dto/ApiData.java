package es.deusto.ingenieria.sd.rmi.server.dto;
import lombok.*;

import javax.jdo.annotations.PersistenceCapable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Data //poniendo esto, 
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ApiData<T> {

    private int id;
    private T attributes;
   
}

    