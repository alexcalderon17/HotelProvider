package es.deusto.ingenieria.sd.rmi.server.servicios;

import java.rmi.RemoteException;
import java.util.List;

import es.deusto.ingenieria.sd.rmi.comun.dto.UsuarioDTO;
import es.deusto.ingenieria.sd.rmi.server.dto.ApiData;
import es.deusto.ingenieria.sd.rmi.server.dto.ApiHabitacionDTO;
import es.deusto.ingenieria.sd.rmi.server.jdo.Usuario;


public interface ServicioUsuario {
    void registrarse(UsuarioDTO usuarioDTO);
    UsuarioDTO iniciarSesion(String usuario, String contrasenya);
    Usuario leerUsuario(String correo);
}