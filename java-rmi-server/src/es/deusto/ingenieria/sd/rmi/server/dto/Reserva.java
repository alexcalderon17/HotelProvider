package es.deusto.ingenieria.sd.rmi.server.dto;

import java.util.Date;
import lombok.*;
import javax.jdo.annotations.PersistenceCapable;
import java.io.Serializable;
import java.util.Date;

@PersistenceCapable
@AllArgsConstructor

public class Reserva {
    private String reservaID;
    private String clienteID;
    private String alojamiento;
    private Date fechaInicio;
    private Date fechaFin;
    private boolean estaCancelada;

    public Reserva() {

    }

    public Reserva(String reservaID, String clienteID, String alojamiento, Date fechaInicio, Date fechaFin) {
        this.reservaID = reservaID;
        this.clienteID = clienteID;
        this.alojamiento = alojamiento;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.estaCancelada = false;
    }

    public Reserva(String clienteID, String alojamiento, Date fechaInicio, Date fechaFin) {

        this.clienteID = clienteID;
        this.alojamiento = alojamiento;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;

    }

    public String getReservaID() {
        return reservaID;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getAlojamiento() {
        return alojamiento;
    }

    public void setAlojamiento(String alojamiento) {
        this.alojamiento = alojamiento;
    }

    public boolean isEstaCancelada() {
        return estaCancelada;
    }

    public void setEstaCancelada(boolean estaCancelada) {
        this.estaCancelada = estaCancelada;
    }

    @Override
    public String toString() {
        return "Reserva{" +
                "reservaID='" + reservaID + '\'' +
                ", clienteID='" + clienteID + '\'' +
                ", alojamiento='" + alojamiento + '\'' +
                ", fechaInicio=" + fechaInicio +
                ", fechaFin=" + fechaFin +
                ", estaCancelada=" + estaCancelada +
                '}';
    }
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