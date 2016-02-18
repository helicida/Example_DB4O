package db4o_Futbol;

/**
 * Created by 46465442z on 18/02/16.
 */
public class Equipo {

    private String nombre;
    private String estadio;

    // Constructores

    public Equipo(String nombre, String estadio){
        this.nombre = nombre;
        this.estadio = estadio;
    }

    public Equipo(){

    }

    // Métodes

    public void cambioEquipo(){

    }

    // Getters

    public String getNombre() {
        return nombre;
    }

    public String getEstadio() {
        return estadio;
    }

    // Setters

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEstadio(String estadio) {
        this.estadio = estadio;
    }
}
