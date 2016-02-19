package db4o_Futbol;

import java.util.ArrayList;

/**
 * Created by 46465442z on 18/02/16.
 */
public class Liga {

    private String nombre;          // Nombre de la liga
    private byte categoria;         // Categoría de la liga
    private String patrocinador;    // Patrocinador de la liga
    ArrayList<Equipo> listaEquipos; // ArrayList con la lista de equipos de la liga

    // Constructores

    public Liga(String nombre, byte categoria, String patrocinador){
        this.nombre = nombre;
        this.categoria = categoria;
        this.patrocinador = patrocinador;
    }

    public Liga(){

    }

    // Métodes

    public void cambioPatrocinador(){

    }

    public void anyadirEquipo(){

    }

    public void eliminarEquipo(){

    }

    public void cambiarEquipo(){
        anyadirEquipo();
        eliminarEquipo();
    }
}
