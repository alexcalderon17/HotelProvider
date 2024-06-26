package es.deusto.ingenieria.sd.rmi.server.servicios.impl;

import java.rmi.RemoteException;

import es.deusto.ingenieria.sd.rmi.comun.dto.UsuarioDTO;
import es.deusto.ingenieria.sd.rmi.server.dao.UsuarioDAO;
import es.deusto.ingenieria.sd.rmi.server.exceptions.ErrorLogin;
import es.deusto.ingenieria.sd.rmi.server.jdo.Usuario;
import es.deusto.ingenieria.sd.rmi.server.servicios.ServicioUsuario;
import es.deusto.ingenieria.sd.rmi.server.utils.UsuarioConverter;

public class ServicioUsuarioImpl implements ServicioUsuario{
    private UsuarioDAO usuarioDAO;

    public ServicioUsuarioImpl(){
        this.usuarioDAO = new UsuarioDAO();
        
    }
   
    @Override
    public void registrarse(UsuarioDTO usuarioDTO) {
        System.out.println("Empezando metodo registrarse de servicioUsuarioImpl");
        Usuario usuario = Usuario.builder()
            .dni(usuarioDTO.getDNI())
            .nombre(usuarioDTO.getNombre())
            .apellido(usuarioDTO.getApellido())
            .correo(usuarioDTO.getCorreo())
            .telefono(usuarioDTO.getTelefono())
            .password(usuarioDTO.getPassword())
            .build();
        validarUsuario(usuario);

        try {
            usuarioDAO.insertarUsuarioDTODB(usuario);
            System.out.println("Usuario creado exitosamente.");
        } catch (Exception e) {
            throw new RuntimeException("Error al crear usuario: " + e.getMessage(), e);
        }

    }
   
    @Override
    public UsuarioDTO iniciarSesion(String correo, String contrasenya){
        System.out.println("Empezando metodo inicarSesion de servicioUsuarioImpl");
        Usuario usuario = usuarioDAO.verificarLogin(correo, contrasenya);
        return UsuarioConverter.convertirUsuarioaDTO(usuario);
    }

    private void validarUsuario(Usuario usuario){
        if (usuario.getCorreo() == null || usuario.getCorreo().isEmpty()) {
            throw new RuntimeException("El correo no puede estar vacío."); 
        }

        //if (usuarioDAO.existeCorreo(correo)) {
          //  throw new RemoteException("El correo ya está en uso.");
       // }

    }

    @Override
    public Usuario leerUsuario(String correo) {
        return usuarioDAO.leerUsuario(correo); 
    }
}
