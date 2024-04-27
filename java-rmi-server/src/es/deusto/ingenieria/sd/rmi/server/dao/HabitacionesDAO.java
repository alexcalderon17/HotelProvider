package es.deusto.ingenieria.sd.rmi.server.dao;

import javax.jdo.Extent;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.Transaction;
import es.deusto.ingenieria.sd.rmi.server.dto.HabitacionDTO;

public class HabitacionesDAO {

    PersistenceManagerFactory persistentManagerFactory = JDOHelper
            .getPersistenceManagerFactory("datanucleus.properties");

    public void insertarHabitacionDTODB(int id, String nombre, String descripcion, int aforo) {
        PersistenceManager persistentManager = persistentManagerFactory.getPersistenceManager();
        Transaction transaction = persistentManager.currentTransaction();

        try {
            transaction.begin();

            HabitacionDTO habitacion = new HabitacionDTO(id, nombre, descripcion, aforo);
            /* SETTERS */

            persistentManager.makePersistent(habitacion);

            System.out.println("+ Inserted habitacion into db: " + habitacion.getNombre());

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

    public void actualizarHabitacionDTODB(int id, String nombre, String descripcion, int aforo) {
        PersistenceManager persistentManager = persistentManagerFactory.getPersistenceManager();
        Transaction transaction = persistentManager.currentTransaction();
        try {
            transaction.begin();
            HabitacionDTO habitacion = persistentManager.getObjectById(HabitacionDTO.class, id);
            if (habitacion != null) {

                // alojamiento.setAlojamiento(alojamiento);
                habitacion.setNombre(nombre);
                habitacion.setDescripcion(descripcion);
                habitacion.setAforo(aforo);
                persistentManager.makePersistent(habitacion);
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

    public void borrarHabitacionDTODB(int id) {
        PersistenceManager persistentManager = persistentManagerFactory.getPersistenceManager();
        Transaction transaction = persistentManager.currentTransaction();

        try {
            transaction.begin();
            HabitacionDTO habitacion = persistentManager.getObjectById(HabitacionDTO.class, id);
            if (habitacion != null) {
                persistentManager.deletePersistent(habitacion);
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
