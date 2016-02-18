package db4o_Futbol;

/**
 * Created by 46465442z on 18/02/16.
 */
public class Liga {

    private String nombre;
    private byte categoria;
    private String patrocinador;

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
}
