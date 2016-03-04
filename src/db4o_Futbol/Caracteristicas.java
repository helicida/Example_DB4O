package db4o_Futbol;

/**
 * Created by 46465442z on 18/02/16.
 */
public class Caracteristicas {

    private byte agilidad;    // Agilidad del jugador
    private byte fuerza;      // fuerza del jugador
    private byte velocidad;   // velocidad del jugador
    private byte pase;        // pase del jugador
    private byte penalti;     // precision en los penaltis del jugador

    // Constructores

    public Caracteristicas(){

    }

    public Caracteristicas(byte agilidad, byte fuerza, byte velocidad, byte pase, byte penalti){
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

    public byte getAgilidad() {
        return agilidad;
    }

    public byte getFuerza() {
        return fuerza;
    }

    public byte getVelocidad() {
        return velocidad;
    }

    public byte getPase() {
        return pase;
    }

    public byte getPenalti() {
        return penalti;
    }

    // Setters

    public void setAgilidad(byte agilidad) {
        this.agilidad = agilidad;
    }

    public void setFuerza(byte fuerza) {
        this.fuerza = fuerza;
    }

    public void setVelocidad(byte velocidad) {
        this.velocidad = velocidad;
    }

    public void setPase(byte pase) {
        this.pase = pase;
    }

    public void setPenalti(byte penalti) {
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
