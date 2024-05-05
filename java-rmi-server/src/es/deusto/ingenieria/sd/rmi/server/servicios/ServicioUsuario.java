package es.deusto.ingenieria.sd.rmi.server.servicios;

import java.rmi.RemoteException;
import java.util.List;

import es.deusto.ingenieria.sd.rmi.comun.dto.UsuarioDTO;
import es.deusto.ingenieria.sd.rmi.server.dto.ApiData;
import es.deusto.ingenieria.sd.rmi.server.dto.HabitacionDTO;

public interface ServicioUsuario {
    void registrarse(UsuarioDTO usuarioDTO);
    boolean iniciarSesion(String usuario, String contrasenya);
}