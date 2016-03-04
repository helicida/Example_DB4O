package db4o_Futbol;

/**
 * Created by 46465442z on 18/02/16.
 */
public class Caracteristicas {

    private int agilidad;    // Agilidad del jugador
    private int fuerza;      // fuerza del jugador
    private int velocidad;   // velocidad del jugador
    private int pase;        // pase del jugador
    private int penalti;     // precision en los penaltis del jugador

    // Constructores

    public Caracteristicas(){

    }

    public Caracteristicas(int agilidad, int fuerza, int velocidad, int pase, int penalti){
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

    public int getAgilidad() {
        return agilidad;
    }

    public int getFuerza() {
        return fuerza;
    }

    public int getVelocidad() {
        return velocidad;
    }

    public int getPase() {
        return pase;
    }

    public int getPenalti() {
        return penalti;
    }

    // Setters

    public void setAgilidad(int agilidad) {
        this.agilidad = agilidad;
    }

    public void setFuerza(int fuerza) {
        this.fuerza = fuerza;
    }

    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }

    public void setPase(int pase) {
        this.pase = pase;
    }

    public void setPenalti(int penalti) {
        this.penalti = penalti;
    }

    // ToString

    public String toString()  {
        return "CARACTERISTICAS:" +
                "\n Agilidad: " + agilidad +
                "\n Fuerza: " + fuerza +
                "\n Velocidad: " + velocidad +
                "\n Pase: " + pase +
                "\n Penalti: " + penalti;
    }
}
