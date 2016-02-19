package db4o_Futbol;

import java.util.ArrayList;

/**
 * Created by 46465442z on 18/02/16.
 */
public class Equipo {

    private String nombre;                      // Nombre del equipo
    private String estadio;                     // Nombre del estadio del equipo
    private Entrenador entrenadorEquipo;        // Entrenador del equipo
    private ArrayList<Jugador> listaJugadores;  // Lista con los jugadores del equipo

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
