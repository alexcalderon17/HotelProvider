package es.deusto.ingenieria.sd.rmi.server.dao;

import es.deusto.ingenieria.sd.rmi.server.dto.ReservaDTO;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;
import javax.jdo.Query;
import java.util.List;
import java.util.Date;

public class ReservaDAO {

    PersistenceManagerFactory persistentManagerFactory = JDOHelper
            .getPersistenceManagerFactory("datanucleus.properties");

    public void insertarReserva(String clienteID, String alojamiento, Date fechaInicio, Date fechaFin) {
        PersistenceManager pm = persistentManagerFactory.getPersistenceManager();
        Transaction tx = pm.currentTransaction();
        try {
            tx.begin();
            ReservaDTO reserva = new ReservaDTO(); 
            reserva.setAlojamiento(alojamiento);
            reserva.setFechaInicio(fechaInicio);
            reserva.setFechaFin(fechaFin);
            reserva.setEstaCancelada(false); 

            pm.makePersistent(reserva);
            System.out.println("+ Inserted reserva into db: " + reserva.getReservaID());
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

   

    public void actualizarReserva(String reservaID, String alojamiento, Date fechaInicio, Date fechaFin,
            boolean estaCancelada) {
        PersistenceManager pm = persistentManagerFactory.getPersistenceManager();
        Transaction tx = pm.currentTransaction();
        try {
            tx.begin();
            ReservaDTO reserva = pm.getObjectById(ReservaDTO.class, reservaID);
            if (reserva != null) {

                reserva.setAlojamiento(alojamiento);
                reserva.setFechaInicio(fechaInicio);
                reserva.setFechaFin(fechaFin);
                reserva.setEstaCancelada(estaCancelada);
                pm.makePersistent(reserva);
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

    public void borrarReserva(String reservaID) {
        PersistenceManager pm = persistentManagerFactory.getPersistenceManager();
        Transaction tx = pm.currentTransaction();
        try {
            tx.begin();
            ReservaDTO reserva = pm.getObjectById(ReservaDTO.class, reservaID);
            if (reserva != null) {
                pm.deletePersistent(reserva);
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

}