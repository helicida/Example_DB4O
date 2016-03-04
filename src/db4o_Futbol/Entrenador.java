package db4o_Futbol;

/**
 * Created by 46465442z on 18/02/16.
 */
public class Entrenador {

    private String nombre;        // Nombre del entrenador
    private int anyoExperiencia; // Anyos de experiencia

    // Constructores

    public Entrenador(String nombre, int anyoExperiencia){
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

    public int getAnyoExperiencia() {
        return anyoExperiencia;
    }


    // Setters

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setAnyoExperiencia(int anyoExperiencia) {
        this.anyoExperiencia = anyoExperiencia;
    }


    // ToString

    public String toString()  {
        return "ENTRENADOR:" +
                "\n Nombre: " + nombre +
                "\n Años experiencia: " + anyoExperiencia;
    }

}
