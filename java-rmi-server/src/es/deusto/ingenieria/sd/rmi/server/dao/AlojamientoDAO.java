/*package es.deusto.ingenieria.sd.rmi.server.dao;

import es.deusto.ingenieria.sd.rmi.server.dto.AlojamientoDTO;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;
import javax.jdo.Query;
import java.util.List;
import java.util.Date;

public class AlojamientoDAO {

    PersistenceManagerFactory persistentManagerFactory = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");

    public void insertarAlojamiento(int id, String nombre, String descripcion, String direccion) {
        PersistenceManager pm = persistentManagerFactory.getPersistenceManager();
        Transaction tx = pm.currentTransaction();
        try {
            tx.begin();
            AlojamientoDTO alojamiento = new AlojamientoDTO(); // Creación del objeto sin inicializar los atributos en el constructor

            alojamiento.setId(id);
            alojamiento.setNombre(nombre);
            alojamiento.setDescripcion(descripcion);
            alojamiento.setDireccion(direccion); // Asumiendo que una nueva alojamiento no está cancelada por defecto

            pm.makePersistent(alojamiento);
            System.out.println("+ Inserted alojamiento into db: " + alojamiento.getId());
            tx.commit();
        } catch (Exception e) {
            System.err.println("DBException: " + e.getMessage());
        } finally {
            if (tx.isActive()) {
                tx.rollback();
            }
            pm.close();
        }
    }

    // public Alojamiento selectAlojamiento(String AlojamientoID) {
    // PersistenceManager pm = pmf.getPersistenceManager();
    // try {
    // return pm.getObjectById(Alojamiento.class, AlojamientoID);
    // } finally {
    // pm.close();
    // }

    public void actualizarAlojamiento(int id, String nombre, String descripcion, String direccion) {
        PersistenceManager pm = persistentManagerFactory.getPersistenceManager();
        Transaction tx = pm.currentTransaction();
        try {
            tx.begin();
            AlojamientoDTO alojamiento = pm.getObjectById(AlojamientoDTO.class, id);
            if (alojamiento != null) {

                alojamiento.setId(id);
                alojamiento.setNombre(nombre);
                alojamiento.setDescripcion(descripcion);
                alojamiento.setDireccion(direccion);
                pm.makePersistent(alojamiento);
            }
            tx.commit();
        } catch (Exception e) {
            System.err.println("DBException: " + e.getMessage());
        } finally {
            if (tx.isActive()) {
                tx.rollback();
            }
            pm.close();
        }
    }

    public void borrarAlojamiento(int AlojamientoID) {
        PersistenceManager pm = persistentManagerFactory.getPersistenceManager();
        Transaction tx = pm.currentTransaction();

        try {
            tx.begin();
            AlojamientoDTO alojamiento = pm.getObjectById(AlojamientoDTO.class, AlojamientoID);
            if (alojamiento != null) {
                pm.deletePersistent(alojamiento);
            }
            tx.commit();
        } catch (Exception e) {
            System.err.println("DBException: " + e.getMessage());
        } finally {
            if (tx.isActive()) {
                tx.rollback();
            }
            pm.close();
        }
    }

}*/