package es.deusto.ingenieria.sd.rmi.server.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

import javax.jdo.Extent;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.Transaction;


import es.deusto.ingenieria.sd.rmi.server.dto.ApiData;
import es.deusto.ingenieria.sd.rmi.server.jdo.Usuario;


public class UsuarioDAO {

    private static final String URL = "jdbc:mysql://viaduct.proxy.rlwy.net:40532/railway";
    private static final String MyUserBD = "root";
    private static final String MyPassBD = "CkVoSrQWwvFYZFFHYeJAvePmMAQihtJL"; 

    PersistenceManagerFactory persistentManagerFactory = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");

   
    public boolean insertarUsuarioDTODB(Usuario usuario) {
        System.out.println("Empezando metodo de insertarUsuarioDTODB en UsuarioDAO ");

        boolean usuarioCreado = false;
        PersistenceManager persistentManager = persistentManagerFactory.getPersistenceManager();
        Transaction transaction = persistentManager.currentTransaction();

        try {
            transaction.begin();
            persistentManager.makePersistent(usuario);
            System.out.println("+ Inserted usuario into db: " + usuario.getCorreo());
            transaction.commit();
            usuarioCreado = true;
        } catch (Exception e) {
            System.err.println("DBException: " + e.getMessage());
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            persistentManager.close();
        }
        return usuarioCreado;
    }

    public void actualizarUsuarioDTODB(String nombre, String apellido, String DNI, String correo, String telefono,
            String password, int codPostal) {
        PersistenceManager persistentManager = persistentManagerFactory.getPersistenceManager();
        Transaction transaction = persistentManager.currentTransaction();
        try {
            transaction.begin();
            Usuario usuario = persistentManager.getObjectById(Usuario.class, correo);
            if (usuario != null) {

               
                usuario.setNombre(nombre);
                usuario.setApellido(apellido);
                usuario.setDni(DNI);
                usuario.setCorreo(correo);
                //usuario.setCodPostal(codPostal);
                usuario.setTelefono(telefono);
                usuario.setPassword(password);
                persistentManager.makePersistent(usuario);
            }
            transaction.commit();
        } catch (Exception e) {
            System.err.println("DBException: " + e.getMessage());
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            persistentManager.close();
        }
    }

    public void borrarUsuarioDTODB(int correo) {
        PersistenceManager persistentManager = persistentManagerFactory.getPersistenceManager();
        Transaction transaction = persistentManager.currentTransaction();

        try {
            transaction.begin();
            Usuario usuario = persistentManager.getObjectById(Usuario.class, correo);
            if (usuario != null) {
                persistentManager.deletePersistent(usuario);
            }
            transaction.commit();
        } catch (Exception e) {
            System.err.println("DBException: " + e.getMessage());
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            persistentManager.close();
        }
    }

    public boolean verificarLogin(String correo, String password) {
        boolean loginExitoso = false;
        String sql = "SELECT * FROM USUARIO WHERE correo = ? AND password = ?";
        try (Connection conn = DriverManager.getConnection(URL, MyUserBD, MyPassBD);
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, correo);
            stmt.setString(2, password);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) { 
                    loginExitoso = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return loginExitoso;
    }

    public boolean existeCorreo(String correo) {
        String sql = "SELECT COUNT(*) FROM usuario WHERE correo = ?";
        try (Connection conn = DriverManager.getConnection(URL, MyUserBD, MyPassBD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, correo);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al verificar el correo: " + e.getMessage());
        }
        return false;
    }


}
