package es.deusto.ingenieria.sd.rmi.server.manager;

import es.deusto.ingenieria.sd.rmi.server.dao.UsuarioDAO;

public class ClienteManager {

    UsuarioDAO u;

    public void registrarse(String nombre, String apellido, String DNI, String correo, String telefono, String password,
            int codPostal) {
        u.insertarUsuarioDTODB(nombre, apellido, DNI, correo, telefono, password, codPostal);

    }

}