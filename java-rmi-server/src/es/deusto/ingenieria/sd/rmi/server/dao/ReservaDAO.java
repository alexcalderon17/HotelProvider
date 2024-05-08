package es.deusto.ingenieria.sd.rmi.server.dao;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;

import es.deusto.ingenieria.sd.rmi.comun.dto.ReservaDTO;

import javax.jdo.Query;
import java.util.List;
import java.util.Date;

public class ReservaDAO {

    PersistenceManagerFactory persistentManagerFactory = JDOHelper
            .getPersistenceManagerFactory("datanucleus.properties");

    public void insertarReserva(String cliente, String alojamiento, String habitacion, Date fechaInicio, Date fechaFin) {
        PersistenceManager pm = persistentManagerFactory.getPersistenceManager();
        Transaction tx = pm.currentTransaction();
        try {
            tx.begin();
            ReservaDTO reserva = new ReservaDTO();
            
            reserva.setCliente(cliente);
            reserva.setAlojamiento(alojamiento);
            reserva.setHabitacion(habitacion);
            reserva.setFechaInicio(fechaInicio);
            reserva.setFechaFin(fechaFin);
             

            pm.makePersistent(reserva);
            System.out.println("+ Inserted reserva into db: " + reserva.getHabitacion());
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

   

    /*public void actualizarReserva(String alojamiento, Date fechaInicio, Date fechaFin,
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
    }*/

    /*public void borrarReserva(String reservaID) {
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
    }*/

}