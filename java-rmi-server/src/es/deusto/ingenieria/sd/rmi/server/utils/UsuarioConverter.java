package es.deusto.ingenieria.sd.rmi.server.utils;

import es.deusto.ingenieria.sd.rmi.comun.dto.UsuarioDTO;
import es.deusto.ingenieria.sd.rmi.server.jdo.Usuario;

public class UsuarioConverter {

    /**
     * Convierte un UsuarioDTO a un Usuario.
     * @param usuarioDTO el DTO de usuario que contiene la información para crear un Usuario.
     * @return un nuevo objeto Usuario con los datos del DTO.
     */
    public static Usuario convertirDTOaUsuario(UsuarioDTO usuarioDTO) {
        if (usuarioDTO == null) {
            return null;
        }
        
        // Crear un Usuario usando el builder
        Usuario usuario = Usuario.builder()
                
                .dni(usuarioDTO.getDNI())
                .nombre(usuarioDTO.getNombre())
                .apellido(usuarioDTO.getApellido())
                .correo(usuarioDTO.getCorreo())
                .telefono(usuarioDTO.getTelefono())
                .password(usuarioDTO.getPassword())
                .build();
        
        return usuario;
    }

    public static UsuarioDTO convertirUsuarioaDTO(Usuario usuario) {
        if (usuario == null) {
            return null;
        }
        // Crear un Usuario usando el builder
        UsuarioDTO usuarioDTO = UsuarioDTO.builder()
                .DNI(usuario.getDni())
                .nombre(usuario.getNombre())
                .apellido(usuario.getApellido())
                .correo(usuario.getCorreo())
                .telefono(usuario.getTelefono())
                .password(usuario.getPassword())
                .build();
        
        return usuarioDTO;
    }
}
