package es.deusto.ingenieria.sd.rmi.server.dto;

public class HabitacionDTO {

    private int id;
    private String nombre;
    private String descripcion;
    private int aforo;
    //private Image descripcion;
    
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
    public int getAforo() {
        return aforo;
    }
    public void setAforo(int aforo) {
        this.aforo = aforo;
    }

}
