package es.deusto.ingenieria.sd.rmi.server.dto;

import java.util.Date;

import lombok.*;
import java.io.Serializable;

@AllArgsConstructor
public class Reserva implements Serializable {
	private static final long serialVersionUID = 227L;
	private int id;
}
/*
 * public class Reserva {
 * private int id;
 * private Date fechaReserva;
 * private Date fechaEntrada;
 * private Date fechaSalida;
 * private double importe;
 * private int numViajeros;
 * private String destino;
 * 
 * public int getId() {
 * return id;
 * }
 * 
 * public void setId(int id) {
 * this.id = id;
 * }
 * 
 * public Date getFechaReserva() {
 * return fechaReserva;
 * }
 * 
 * public void setFechaReserva(Date fechaReserva) {
 * this.fechaReserva = fechaReserva;
 * }
 * 
 * }
 */
