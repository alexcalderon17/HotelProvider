package es.deusto.ingenieria.sd.rmi.server.servicios.impl;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

import es.deusto.ingenieria.sd.rmi.comun.dto.ReservaDTO;
import es.deusto.ingenieria.sd.rmi.server.dao.ReservaDAO;
import es.deusto.ingenieria.sd.rmi.server.dao.UsuarioDAO;
import es.deusto.ingenieria.sd.rmi.server.dto.ApiData;
import es.deusto.ingenieria.sd.rmi.server.dto.HabitacionDTO;
import es.deusto.ingenieria.sd.rmi.server.jdo.Usuario;
import es.deusto.ingenieria.sd.rmi.server.jdo.Reserva;


import java.util.Date;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import es.deusto.ingenieria.sd.rmi.server.servicios.ServicioReserva;

public class ServicioReservaImpl implements ServicioReserva {
    private ReservaDAO reservaDAO;

    public ServicioReservaImpl(){
        this.reservaDAO = new ReservaDAO();
        
    }

    @Override
    public void guardarReserva (ReservaDTO reservaDTO) {
        // Crear un nuevo objeto Reserva con los datos proporcionados
        Reserva reserva = Reserva.builder()
        .cliente(reservaDTO.getCliente())
        .alojamiento(reservaDTO.getAlojamiento())
        .habitacion(reservaDTO.getHabitacion())
        .fechaInicio(reservaDTO.getFechaInicio())
        .fechaFin(reservaDTO.getFechaFin())
        .build();

        try {
            reservaDAO.insertarReserva(reserva);
            System.out.println("Reserva gaurdada exitosamente.");
        } catch (Exception e) {
            throw new RuntimeException("Error al guardar la reserva: " + e.getMessage(), e);
        }
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
