package db4o_Futbol;

/**
 * Created by 46465442z on 18/02/16.
 */
public class Caracteristicas {

    private String agilidad;    // Agilidad del jugador
    private String fuerza;      // fuerza del jugador
    private String velocidad;   // velocidad del jugador
    private String pase;        // pase del jugador
    private String penalti;     // precision en los penaltis del jugador

    // Constructores

    public Caracteristicas(){

    }

    public Caracteristicas(String agilidad, String fuerza, String velocidad, String pase, String penalti){
        this.agilidad = agilidad;
        this.fuerza = fuerza;
        this.velocidad = velocidad;
        this.pase = pase;
        this.penalti = penalti;
    }

    // Métodes

    public void aumentarCaracterísticas(){

    }

    // Getters

    public String getAgilidad() {
        return agilidad;
    }

    public String getFuerza() {
        return fuerza;
    }

    public String getVelocidad() {
        return velocidad;
    }

    public String getPase() {
        return pase;
    }

    public String getPenalti() {
        return penalti;
    }

    // Setters

    public void setAgilidad(String agilidad) {
        this.agilidad = agilidad;
    }

    public void setFuerza(String fuerza) {
        this.fuerza = fuerza;
    }

    public void setVelocidad(String velocidad) {
        this.velocidad = velocidad;
    }

    public void setPase(String pase) {
        this.pase = pase;
    }

    public void setPenalti(String penalti) {
        this.penalti = penalti;
    }
}
