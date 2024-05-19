package es.deusto.ingenieria.sd.rmi.server.servicios.impl;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Random;

import es.deusto.ingenieria.sd.rmi.comun.dto.ReservaDTO;
import es.deusto.ingenieria.sd.rmi.comun.dto.UsuarioDTO;

import es.deusto.ingenieria.sd.rmi.server.dao.ReservaDAO;
import es.deusto.ingenieria.sd.rmi.server.dao.UsuarioDAO;
import es.deusto.ingenieria.sd.rmi.server.dto.ApiData;
import es.deusto.ingenieria.sd.rmi.server.dto.ApiHabitacionDTO;
import es.deusto.ingenieria.sd.rmi.server.exceptions.ErrorCreacionReserva;
import es.deusto.ingenieria.sd.rmi.server.exceptions.ErrorLecturaBaseDatos;
import es.deusto.ingenieria.sd.rmi.server.jdo.Usuario;
import es.deusto.ingenieria.sd.rmi.server.jdo.Reserva;
import java.util.Random;



import java.util.Date;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import es.deusto.ingenieria.sd.rmi.server.servicios.ServicioReserva;
import es.deusto.ingenieria.sd.rmi.server.servicios.ServicioUsuario;
import es.deusto.ingenieria.sd.rmi.server.utils.UsuarioConverter;

public class ServicioReservaImpl implements ServicioReserva {
    private ReservaDAO reservaDAO;
    private ServicioUsuario servicioUsuario;


    public ServicioReservaImpl(){
        this.reservaDAO = new ReservaDAO();
        this.servicioUsuario = new ServicioUsuarioImpl();
    }

    @Override
    public void guardarReserva (ReservaDTO reservaDTO, UsuarioDTO usuarioDTO) {
        // Crear un nuevo objeto Reserva con los datos proporcionados
        Reserva reserva = Reserva.builder()
            .codigoReserva(crearCodigoReserva())
            .alojamiento(reservaDTO.getAlojamiento())
            .habitacion(reservaDTO.getHabitacion())
            .fechaInicio(reservaDTO.getFechaInicio())
            .fechaFin(reservaDTO.getFechaFin())
            .build();

        try {
            reservaDAO.insertarReserva(reserva, usuarioDTO.getCorreo());
            System.out.println("Reserva gaurdada exitosamente.");
        } catch (Exception e) {
            throw new RuntimeException("Error al guardar la reserva: " + e.getMessage(), e);
        }
    }

    private String crearCodigoReserva() {
        String letras = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        int longitud = 5;
        Random random = new Random();
        StringBuilder codigoReserva = new StringBuilder(longitud);
        for (int i = 0; i < longitud; i++) {
            int index = random.nextInt(letras.length());
            codigoReserva.append(letras.charAt(index));
        }
        return codigoReserva.toString();
    }
}
