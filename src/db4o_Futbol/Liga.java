package db4o_Futbol;

import java.util.ArrayList;

/**
 * Created by 46465442z on 18/02/16.
 */
public class Liga {

    private String nombre;                                  // Nombre de la liga
    private int categoria;                                 // Categoría de la liga
    private String patrocinador;                            // Patrocinador de la liga
    private ArrayList<Equipo> equipos = new ArrayList<>();  // Equipos que contiene la liga

    // Constructores

    public Liga(String nombre, int categoria, String patrocinador){
        this.nombre = nombre;
        this.categoria = categoria;
        this.patrocinador = patrocinador;
    }

    public Liga(){

    }

    // Métodes

    public void anyadirEqupio(Equipo equipoAnyadir){
        equipos.add(equipoAnyadir);
    }

    public void cambioPatrocinador(){

    }

    // Getters

    public String getNombre() {
        return nombre;
    }

    public int getCategoria() {
        return categoria;
    }

    public String getPatrocinador() {
        return patrocinador;
    }

    public ArrayList<Equipo> getEquipos() {
        return equipos;
    }

    // Setters

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }

    public void setPatrocinador(String patrocinador) {
        this.patrocinador = patrocinador;
    }

    // ToString

    public String toString()  {
        return "LIGA:" +
                "\n Nombre: " + nombre +
                "\n Categoria: " + categoria +
                "\n Patrocinador: " + patrocinador;
    }
}
