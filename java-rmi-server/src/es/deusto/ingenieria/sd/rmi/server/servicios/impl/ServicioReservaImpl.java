package es.deusto.ingenieria.sd.rmi.server.servicios.impl;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

import es.deusto.ingenieria.sd.rmi.comun.dto.ReservaDTO;
import es.deusto.ingenieria.sd.rmi.server.dto.ApiData;
import es.deusto.ingenieria.sd.rmi.server.dto.HabitacionDTO;

import java.util.Date;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import es.deusto.ingenieria.sd.rmi.server.servicios.ServicioReserva;

public class ServicioReservaImpl implements ServicioReserva {

    @Override
    public ReservaDTO crearReserva(int reservaID, String cliente, String alojamiento,String habitacion,  Date fechaInicio, Date fechaFin) {
        // Crear un nuevo objeto Reserva con los datos proporcionados
        return new ReservaDTO(reservaID, cliente, alojamiento, habitacion, fechaInicio, fechaFin);
    }

    /*@Override
    public void cancelarReserva(ReservaDTO reserva) {
        if (!reserva.isEstaCancelada()) {
            reserva.setEstaCancelada(true);
            System.out.println("La reserva ha sido cancelada exitosamente.");
        } else {
            System.out.println("Esta reserva ya estaba cancelada.");
        }
    }*/

    /*@Override
    public boolean modificarReserva(String reservaID, Date nuevaFechaInicio, Date nuevaFechaFin) {
        HashMap<String, ReservaDTO> reservas = new HashMap<>();

        ReservaDTO reserva = reservas.get(reservaID);
        if (reserva != null && !reserva.isEstaCancelada()) {
            reserva.setFechaInicio(nuevaFechaInicio);
            reserva.setFechaFin(nuevaFechaFin);
            System.out.println("Reserva modificada exitosamente.");
            return true;
        } else if (reserva == null) {
            System.out.println("No se encontró la reserva con ID: " + reservaID);
            return false;
        } else {
            System.out.println("No se puede modificar una reserva cancelada.");
            return false;
        }
    }*/

    // @Override
    // public List<Reserva> obtenerReservasUsuario (String usuarioID){

    // }
}
