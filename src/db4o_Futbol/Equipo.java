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
    private Liga liga;                          // Liga en la que está el equipo

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

    public void anyadirJugador(Jugador jugador){
        listaJugadores.add(jugador);
    }

    // Getters

    public String getNombre() {
        return nombre;
    }

    public String getEstadio() {
        return estadio;
    }

    public Entrenador getEntrenadorEquipo() {
        return entrenadorEquipo;
    }

    public Liga getLiga() {
        return liga;
    }

    public ArrayList<Jugador> getListaJugadores() {
        return listaJugadores;
    }


// Setters

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEstadio(String estadio) {
        this.estadio = estadio;
    }

    public void setEntrenadorEquipo(Entrenador entrenadorEquipo) {
        this.entrenadorEquipo = entrenadorEquipo;
    }

    public void setLiga (Liga liga){
        this.liga = liga;
    }

    public void setListaJugadores(ArrayList<Jugador> listaJugadores) {
        this.listaJugadores = listaJugadores;
    }
}
