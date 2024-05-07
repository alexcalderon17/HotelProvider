package es.deusto.ingenieria.sd.rmi.server.dto;

import lombok.*;

import java.util.Date;
import javax.jdo.annotations.PersistenceCapable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ApiData<T> {

    private int id;
    private T attributes;

 
  
}

    