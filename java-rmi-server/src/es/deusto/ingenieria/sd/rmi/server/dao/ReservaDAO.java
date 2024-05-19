package es.deusto.ingenieria.sd.rmi.server.dao;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;
import javax.jdo.JDOObjectNotFoundException;

import es.deusto.ingenieria.sd.rmi.comun.dto.ReservaDTO;
import es.deusto.ingenieria.sd.rmi.server.exceptions.ErrorLecturaBaseDatos;
import es.deusto.ingenieria.sd.rmi.server.jdo.Reserva;
import es.deusto.ingenieria.sd.rmi.server.jdo.Usuario;


import javax.jdo.Query;
import java.util.List;
import java.util.Date;

public class ReservaDAO {

    PersistenceManagerFactory persistentManagerFactory = JDOHelper
            .getPersistenceManagerFactory("datanucleus.properties");

    public void insertarReserva(Reserva reserva, String correo) {
        PersistenceManager pm = persistentManagerFactory.getPersistenceManager();
        Transaction tx = pm.currentTransaction();
        try {
            tx.begin();
            Usuario usuario = pm.getObjectById(Usuario.class, correo);
            reserva.setCliente(usuario);
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

    public Reserva leerReserva(String codigo) {
        if (codigo == null || codigo.isBlank()) {
            throw new ErrorLecturaBaseDatos("Error al buscar la reserva con codigo null o blank");
        }
        PersistenceManager persistentManager = persistentManagerFactory.getPersistenceManager();
        Transaction transaction = persistentManager.currentTransaction();
        try {
            transaction.begin();
            persistentManager.getFetchPlan().addGroup("cliente");
            // Buscar la reserva por código
            Reserva reserva = persistentManager.getObjectById(Reserva.class, codigo);
    
            // Si se encuentra la reserva, hacer detach y commit, luego devolver true
            reserva = persistentManager.detachCopy(reserva);
            transaction.commit();
            return reserva;
        } catch (JDOObjectNotFoundException e) {
            // Si no se encuentra la reserva, devolver false
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new ErrorLecturaBaseDatos("No se ha encontrado la reserva con codigo:  " + codigo + "en la BD", e);

        } catch (Exception e) {
            // Manejo de cualquier otra excepción
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new ErrorLecturaBaseDatos("Error al leer reserva de la base de datos", e);
        } finally {
            // Cerrar el PersistenceManager
            persistentManager.close();
        }
    }
    

    
}