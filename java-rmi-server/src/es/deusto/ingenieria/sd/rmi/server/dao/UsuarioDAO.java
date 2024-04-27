package es.deusto.ingenieria.sd.rmi.server.dao;

import javax.jdo.Extent;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.Transaction;
import es.deusto.ingenieria.sd.rmi.server.dto.AlojamientoDTO;
import es.deusto.ingenieria.sd.rmi.server.dto.Usuario;

public class UsuarioDAO {

    PersistenceManagerFactory persistentManagerFactory = JDOHelper
            .getPersistenceManagerFactory("datanucleus.properties");

    // INSERTAR USUARIO relacionado con registrarse ver como
    public void insertarUsuarioDTODB(String nombre, String apellido, String DNI, String correo, String telefono,
            String password, int codPostal) {
        PersistenceManager persistentManager = persistentManagerFactory.getPersistenceManager();
        Transaction transaction = persistentManager.currentTransaction();

        try {
            transaction.begin();

            Usuario usuario = new Usuario(nombre, apellido, DNI, correo, telefono, password, codPostal);
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
            Usuario usuario = persistentManager.getObjectById(Usuario.class, correo);
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
}
