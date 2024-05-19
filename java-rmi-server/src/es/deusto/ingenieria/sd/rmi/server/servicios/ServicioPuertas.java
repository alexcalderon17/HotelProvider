package es.deusto.ingenieria.sd.rmi.server.servicios;

import es.deusto.ingenieria.sd.rmi.comun.dto.UsuarioDTO;

public interface ServicioPuertas {

    byte[] abrirPuerta(String codigoReserva);
} 
