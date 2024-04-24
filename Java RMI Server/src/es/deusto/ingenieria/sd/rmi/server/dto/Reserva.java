package es.deusto.ingenieria.sd.rmi.server.dto;

import lombok.*;
import javax.jdo.annotations.PersistenceCapable;
import java.io.Serializable;

@PersistenceCapable
@AllArgsConstructor
public class Reserva implements Serializable {
	private static final long serialVersionUID = 227L;
	private int id;

}