package es.deusto.ingenieria.sd.rmi.server.dto;

import java.util.Date;

public class Reserva {
	private int id;
	private Date fechaReserva;
	private Date fechaEntrada;
	private Date fechaSalida;
	private double importe;
	private int numViajeros;
	private String destino;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getFechaReserva() {
		return fechaReserva;
	}

	public void setFechaReserva(Date fechaReserva) {
		this.fechaReserva = fechaReserva;
	}

}
