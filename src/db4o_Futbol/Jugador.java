package db4o_Futbol;

/**
 * Created by 46465442z on 18/02/16.
 */
public class Jugador {

    private String DNI;
    private String nombre;
    private String apellido;
    private double altura;

    // Constructores

    public Jugador(String DNI, String nombre, String apellido, double altura){
        this.DNI = DNI;
        this.nombre = nombre;
        this.apellido = apellido;
        this.altura = altura;
    }

    public Jugador(){

    }

    public void SeRetira(){

    }

    // Metodos

    public void traspas(){

    }

    // Getters

    public String getDNI() {
        return DNI;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public double getAltura() {
        return altura;
    }

    // Setters

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }
}
