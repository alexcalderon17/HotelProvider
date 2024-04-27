package es.deusto.ingenieria.sd.rmi.server.dao;

import es.deusto.ingenieria.sd.rmi.server.dto.Reserva;
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
            Reserva reserva = new Reserva(); // Creación del objeto sin inicializar los atributos en el constructor

            reserva.setAlojamiento(alojamiento);
            reserva.setFechaInicio(fechaInicio);
            reserva.setFechaFin(fechaFin);
            reserva.setEstaCancelada(false); // Asumiendo que una nueva reserva no está cancelada por defecto

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

    // public Reserva selectReserva(String reservaID) {
    // PersistenceManager pm = pmf.getPersistenceManager();
    // try {
    // return pm.getObjectById(Reserva.class, reservaID);
    // } finally {
    // pm.close();
    // }

    public void actualizarReserva(String reservaID, String alojamiento, Date fechaInicio, Date fechaFin,
            boolean estaCancelada) {
        PersistenceManager pm = persistentManagerFactory.getPersistenceManager();
        Transaction tx = pm.currentTransaction();
        try {
            tx.begin();
            Reserva reserva = pm.getObjectById(Reserva.class, reservaID);
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
            Reserva reserva = pm.getObjectById(Reserva.class, reservaID);
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