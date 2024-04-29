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
import es.deusto.ingenieria.sd.rmi.server.dto.UsuarioDTO;

public class UsuarioDAO {

    private static final String URL = "jdbc:mysql://viaduct.proxy.rlwy.net:40532/railway";
    private static final String MyUserBD = "root";
    private static final String MyPassBD = "CkVoSrQWwvFYZFFHYeJAvePmMAQihtJL"; // ¿cuanto de seguro ess?

    PersistenceManagerFactory persistentManagerFactory = JDOHelper
            .getPersistenceManagerFactory("datanucleus.properties");

    // INSERTAR USUARIO relacionado con registrarse ver como
    public void insertarUsuarioDTODB(String nombre, String apellido, String DNI, String correo, String telefono,
            String password, int codPostal) {
        PersistenceManager persistentManager = persistentManagerFactory.getPersistenceManager();
        Transaction transaction = persistentManager.currentTransaction();

        try {
            transaction.begin();

            UsuarioDTO usuario = new UsuarioDTO(nombre, apellido, DNI, correo, telefono, password, codPostal);
            /*
             * SETTERS
             */

            persistentManager.makePersistent(usuario);

            System.out.println("+ Inserted usuario into db: " + usuario.getCorreo());

            transaction.commit();

        } catch (Exception e) {
            System.err.println("DBException");
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            persistentManager.close();
        }

    }

    public void actualizarUsuarioDTODB(String nombre, String apellido, String DNI, String correo, String telefono,
            String password, int codPostal) {
        PersistenceManager persistentManager = persistentManagerFactory.getPersistenceManager();
        Transaction transaction = persistentManager.currentTransaction();
        try {
            transaction.begin();
            UsuarioDTO usuario = persistentManager.getObjectById(UsuarioDTO.class, correo);
            if (usuario != null) {

                // alojamiento.setAlojamiento(alojamiento);
                usuario.setNombre(nombre);
                usuario.setApellido(apellido);
                usuario.setDNI(DNI);
                usuario.setCorreo(correo);
                usuario.setCodPostal(codPostal);
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
            UsuarioDTO usuario = persistentManager.getObjectById(UsuarioDTO.class, correo);
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
                if (rs.next()) { // Si hay siguiente linea = que hay linea con esos datos de logeo
                    loginExitoso = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return loginExitoso;

    }
}
