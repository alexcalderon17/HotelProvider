package es.deusto.ingenieria.sd.rmi.server.dto;

import lombok.*;

import java.util.Date;

import javax.jdo.annotations.PersistenceCapable;

@PersistenceCapable
@AllArgsConstructor

public class AlojamientoDTO {

    private int id;
    private String nombre;
    private String descripcion;
    private String direccion;

    /*
     * public AlojamientoDTO(int id, String nombre, String descripcion, String
     * direccion) {
     * this.id = id;
     * this.nombre = nombre;
     * this.descripcion = descripcion;
     * this.direccion = direccion;
     * }
     */

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

}
