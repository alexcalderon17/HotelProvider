<<<<<<< Updated upstream
PersistenceManagerFactory persistentManagerFactory = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");

    public void insertarAlojamientoBD(String nombre, String descripcion, String direccion) {
        PersistenceManager persistentManager = persistentManagerFactory.getPersistenceManager();
        Transaction transaction = persistentManager.currentTransaction();

        try {
            transaction.begin();

            AlojamientoDTO alojamiento = new AlojamientoDTO();
            alojamiento.setNombre(nombre);
            alojamiento.setDescripcion(descripcion);
            alojamiento.setDireccion(direccion);

            persistentManager.makePersistent(alojamiento);

            System.out.println("+ Inserted alojamiento into db: " + alojamiento.getNombre);

            transaction.commit();

        } catch (Exception e) {
            System.err.println("DBException");
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            persistentManager.close();
        }
=======
package es.deusto.ingenieria.sd.rmi.server.dao;

import javax.jdo.Extent;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.Transaction;
import es.deusto.ingenieria.sd.rmi.server.dto.AlojamientoDTO;

public class AlojamientoDAO {

    PersistenceManagerFactory persistentManagerFactory = JDOHelper
            .getPersistenceManagerFactory("datanucleus.properties");

    public void insertarAlojamientoBD(int id, String nombre, String descripcion, String direccion) {
        PersistenceManager persistentManager = persistentManagerFactory.getPersistenceManager();
        Transaction transaction = persistentManager.currentTransaction();

        try {
            transaction.begin();

            AlojamientoDTO alojamiento = new AlojamientoDTO(id, nombre, descripcion, direccion);
            alojamiento.setId(id);
            alojamiento.setNombre(nombre);
            alojamiento.setDescripcion(descripcion);
            alojamiento.setDireccion(direccion);

            persistentManager.makePersistent(alojamiento);

            System.out.println("+ Inserted alojamiento into db: " + alojamiento.getNombre);

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

}

// public void insertarAlojamiento(){
// PersistenceManagerFactory persistentManagerFactory =
// JDOHelper.getPersistenceManagerFactory("datanucleus.properties");

// PersistenceManager persistentManager =
// persistentManagerFactory.getPersistenceManager();
// Transaction transaction = persistentManager.currentTransaction();

// try{
// transaction.begin();

// }
// }

// }
>>>>>>> Stashed changes
