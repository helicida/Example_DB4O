package db4o_Futbol;

import java.util.ArrayList;

/**
 * Created by 46465442z on 18/02/16.
 */
public class Liga {

    private String nombre;          // Nombre de la liga
    private byte categoria;         // Categoría de la liga
    private String patrocinador;    // Patrocinador de la liga

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

    // Getters

    public String getNombre() {
        return nombre;
    }

    public byte getCategoria() {
        return categoria;
    }

    public String getPatrocinador() {
        return patrocinador;
    }


    // Setters

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCategoria(byte categoria) {
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
