package db40_guerra;

public class GrupoArmado {

	private String nombre;
	private int codigo;
	private int bajas;

	// Constructores

	public GrupoArmado(){

	}

	public GrupoArmado (String nombre,int cod, int bajas) {
		this.nombre = nombre;
		this.codigo = cod;
		this.bajas = bajas;
	}

	// Setters

	public void setNombre( String nombre) {
		this.nombre= nombre;
	}

	public void setCodigo( int codigo) {
		this.codigo= codigo;
	}

	public void setBajas( int bajas) {
		this.bajas= bajas;
	}

	// Getters

	public String getNombre() {
		return nombre;
	}

	public  int getCodigo() {
		return codigo;
	}

	public int getBajas() {
		return bajas;
	}


	// To String

	public String toString(){
		return "\nGrupo Armado:" +
				"\n Codigo: " + codigo +
				"\n Nombre: " + nombre +
				"\n Bajas: " + bajas;
	}
}
