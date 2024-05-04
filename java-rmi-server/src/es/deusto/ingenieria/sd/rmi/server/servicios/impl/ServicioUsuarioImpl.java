package es.deusto.ingenieria.sd.rmi.server.servicios.impl;

import java.rmi.RemoteException;

import es.deusto.ingenieria.sd.rmi.server.dao.UsuarioDAO;
import es.deusto.ingenieria.sd.rmi.server.jdo.Usuario;
import es.deusto.ingenieria.sd.rmi.server.servicios.ServicioUsuario;

public class ServicioUsuarioImpl implements ServicioUsuario{
    private UsuarioDAO usuarioDAO;

    public ServicioUsuarioImpl(){
        this.usuarioDAO = new UsuarioDAO();
        
    }
   
    @Override
    public void registrarse(String nombre, String apellido, String DNI, String correo, String telefono, String password) {
        System.out.println("Empezando metodo registrarse de servicio Alojamiento");
        Usuario usuario = new Usuario(nombre, apellido, DNI, correo, telefono, password);
        validarUsuario(usuario);
        // Aquí podríamos añadir más validaciones, como formato del DNI, longitud de la contraseña, etc.

        try {
            usuarioDAO.insertarUsuarioDTODB(usuario);
            System.out.println("Usuario creado exitosamente.");
        } catch (Exception e) {
            throw new RuntimeException("Error al crear usuario: " + e.getMessage(), e);
        }

    }
    private void validarUsuario(Usuario usuario){
        if (usuario.getCorreo() == null || usuario.getCorreo().isEmpty()) {
            throw new RuntimeException("El correo no puede estar vacío."); 
        }

        //if (usuarioDAO.existeCorreo(correo)) {
          //  throw new RemoteException("El correo ya está en uso.");
       // }

    }
}
