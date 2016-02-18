package db4o_Futbol;

/**
 * Created by 46465442z on 18/02/16.
 */
public class Entrenador {

    private String nombre;
    private byte anyoExperiencia;

    // Constructores

    public Entrenador(String nombre, byte anyoExperiencia){
        this.nombre = nombre;
        this.anyoExperiencia = anyoExperiencia;
    }

    public Entrenador(){

    }

    // Métodes

    public void cambioEquipo(){

    }

    // Getters

    public String getNombre() {
        return nombre;
    }

    public byte getAnyoExperiencia() {
        return anyoExperiencia;
    }


    // Setters

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setAnyoExperiencia(byte anyoExperiencia) {
        this.anyoExperiencia = anyoExperiencia;
    }
}
