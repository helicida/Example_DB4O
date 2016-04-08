package db40_guerra;

import java.util.ArrayList;

public class Conflicto {

	private int codigo;
	private String nombre;
	private String zona;
	private int heridos;
	private ArrayList<GrupoArmado> listaGruposArmados = new ArrayList<>();  // Lista con los grupos participes en el conflicto

	// Constructores

	public Conflicto(){

	}

	public Conflicto(int codigoConflicto){
		this.codigo = codigoConflicto;
	}

	public Conflicto(int codigo, String nombre, String zona, int heridos) {
		this.codigo= codigo;
		this.nombre = nombre;
		this.zona = zona;
		this.heridos = heridos;
	}

	// Metodos

	public void anyadirGrupoArmado(GrupoArmado grupoArmado){
		listaGruposArmados.add(grupoArmado);
	}

	// Setters

	public void setCodigo( int codigo) {
		this.codigo= codigo;
	}

	public void setNombre( String nombre) {
		this.nombre= nombre;
	}

	public void setZona( String zona) {
		this.zona= zona;
	}

	public void setHeridos( int heridos) {
		this.heridos= heridos;
	}

	// Getters
	public int getCodigo( ) {
		return codigo;
	}

	public String getNombre( ) {
		return nombre;
	}

	public String getZona( ) {
		return zona;
	}

	public int getHeridos( ) {
		return heridos;
	}

	// To String

	public String toString()  {

		String gruposArmados = "";

		int contador = 0;

		while(contador < listaGruposArmados.size()){

			gruposArmados = gruposArmados + "\n" + listaGruposArmados.get(contador).toString();

			contador ++;
		}

		return "Conflicto:" +
				"\n id: " + codigo +
				"\n Nombre: " + nombre +
				"\n Zona: " + zona +
				"\n Heridos: " + heridos +
				"\n" + gruposArmados;

	}
}
