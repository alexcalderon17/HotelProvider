package es.deusto.ingenieria.sd.rmi.server.manager;

import es.deusto.ingenieria.sd.rmi.server.dao.UsuarioDAO;
import es.deusto.ingenieria.sd.rmi.server.jdo.Usuario;

public class ClienteManager {

    UsuarioDAO usuarioDAO;

    public void registrarse(String nombre, String apellido, String DNI, String correo, String telefono, String password) {
        Usuario usuario = new Usuario(nombre, apellido, DNI, correo, telefono, password);
        usuarioDAO.insertarUsuarioDTODB(usuario);
    }

}